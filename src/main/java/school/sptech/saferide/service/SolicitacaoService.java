package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.contrato.Contrato;
import school.sptech.saferide.model.entity.contrato.ContratoMapper;
import school.sptech.saferide.model.entity.contrato.ContratoRequest;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.mensagem.Mensagem;
import school.sptech.saferide.model.entity.solicitacao.Solicitacao;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoMapper;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoRequest;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoRequestResponsavel;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.StatusSolicitacao;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.model.exception.SolicitacaoAlreadyApproveException;
import school.sptech.saferide.repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static school.sptech.saferide.model.enums.StatusDependente.CONVERSA_CRIADA;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository repository;
    private final UsuarioService usuarioService;
    private final EscolaService escolaService;
    private final EnderecoService enderecoService;
    private final DependenteService dependenteService;
    private final MensagemService mensagemService;
    private final ConversaService conversaService;
    private final ContratoService contratoService;

    public Solicitacao criar(SolicitacaoRequestResponsavel request) {
        Usuario motorista = usuarioService.listarPorId(request.getMotoristaId());
        Usuario responsavel = usuarioService.listarPorId(request.getResponsavelId());
        Endereco endereco = enderecoService.listarPorId(request.getEnderecoId());
        Dependente dependente = dependenteService.listarPorId(request.getDependenteId());
        Escola escola = dependente.getEscola();

        Solicitacao solicitacao = new Solicitacao(
                responsavel, motorista, endereco, escola, dependente,
                request.getPeriodo(), request.getTipo(), request.getDiaSemana());
        return repository.save(solicitacao);
    }

    public Solicitacao atualizar(SolicitacaoRequest request) {
        Solicitacao solicitacao = listarPorId(request.getId());

        solicitacao.setHorarioIda(request.getHorarioIda());
        solicitacao.setHorarioVolta(request.getHorarioVolta());
        solicitacao.setContratoInicio(request.getContratoInicio());
        solicitacao.setContratoFim(request.getContratoFim());
        solicitacao.setValor(request.getValor());
        solicitacao.setStatus(StatusSolicitacao.PENDENTE_RESPONSAVEL);

        return repository.save(solicitacao);
    }

    public void cancelar(Integer id) {
        Solicitacao solicitacao = listarPorId(id);
        solicitacao.setStatus(StatusSolicitacao.CANCELADO);

        repository.save(solicitacao);
    }

    public Solicitacao listarPorId(int solicitacaoId) {
        Optional<Solicitacao> optional = repository.findById(solicitacaoId);
        if (optional.isEmpty()) throw new NotFoundException("Solicitacao");
        return optional.get();
    }

    public List<Solicitacao> listarPorMotorista(int motoristaId) {
        usuarioService.listarPorId(motoristaId);
        return repository.findByMotoristaId(motoristaId);
    }

    public List<Solicitacao> listarPorResponsavel(int responsavelId) {
        usuarioService.listarPorId(responsavelId);
        return repository.findByResponsavelId(responsavelId);
    }

    public Solicitacao listarPorDependente(int dependenteId) {
        dependenteService.listarPorId(dependenteId);
        return repository.findByDependenteIdAndStatusIn(dependenteId, new Integer[]{0,1})
                .orElseThrow(() -> new NotFoundException("Solicitação"));
    }

    public Solicitacao aprovar(int solicitacaoId) {
        Solicitacao solicitacao = listarPorId(solicitacaoId);
        if (solicitacao.getStatus().equals(StatusSolicitacao.APROVADO)) throw new SolicitacaoAlreadyApproveException(solicitacao.getId().toString());
        dependenteService.vincularMotorista(solicitacao.getDependente().getId(), solicitacao.getMotorista().getId());

        Conversa conversa = conversaService.listarEntreMotoristaEResponsavel(
                solicitacao.getResponsavel().getId(), solicitacao.getMotorista().getId());

        if (conversa == null) {
            conversa = conversaService.criar(
                    new Conversa(),
                    solicitacao.getResponsavel().getId(),
                    solicitacao.getMotorista().getId());

            mensagemService.criar(
                    new Mensagem(CONVERSA_CRIADA),
                    conversa.getId(),
                    solicitacao.getMotorista().getId(),
                    solicitacao.getDependente().getId());
        }

        var contrato = contratoService.listarPorResponsavelIdAndMotoristaId(
                solicitacao.getResponsavel().getId(), solicitacao.getMotorista().getId()
        );

        if (contrato.isEmpty()) {
            ContratoRequest contratoRequest = new ContratoRequest(
                    solicitacao.getMotorista().getId(),
                    solicitacao.getResponsavel().getId(),
                    Collections.singletonList(solicitacao.getDependente().getId()),
                    solicitacao.getContratoInicio(),
                    solicitacao.getContratoFim(),
                    solicitacao.getValor().doubleValue()
            );
            contratoService.criar(ContratoMapper.toEntity(contratoRequest),
                    solicitacao.getResponsavel().getId(),
                    solicitacao.getMotorista().getId(),
                    Collections.singletonList(solicitacao.getDependente().getId())
            );
        } else {
            contratoService.atualizar(contrato.get());
        }
        solicitacao.setStatus(StatusSolicitacao.APROVADO);
        return repository.save(solicitacao);
    }

}
