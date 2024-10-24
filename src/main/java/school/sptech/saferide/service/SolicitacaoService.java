package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.contrato.Contrato;
import school.sptech.saferide.model.entity.contrato.ContratoMapper;
import school.sptech.saferide.model.entity.contrato.ContratoRequest;
import school.sptech.saferide.model.entity.contrato.ContratoUpdate;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.mensagem.Mensagem;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.solicitacao.Solicitacao;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoRequest;
import school.sptech.saferide.model.entity.solicitacao.SolicitacaoRequestResponsavel;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import school.sptech.saferide.model.entity.trajeto.TrajetoMapper;
import school.sptech.saferide.model.entity.trajeto.TrajetoRequest;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.DiaSemana;
import school.sptech.saferide.model.enums.StatusSolicitacao;
import school.sptech.saferide.model.enums.TipoTrajeto;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.model.exception.SolicitacaoAlreadyApproveException;
import school.sptech.saferide.repository.*;

import java.util.*;

import static school.sptech.saferide.model.enums.StatusDependente.CONVERSA_CRIADA;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository repository;
    private final UsuarioService usuarioService;
    private final EnderecoService enderecoService;
    private final DependenteService dependenteService;
    private final MensagemService mensagemService;
    private final ConversaService conversaService;
    private final ContratoService contratoService;
    private final TrajetoService trajetoService;
    private final RotaService rotaService;

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

        aprovarHandleConversa(solicitacao);
        aprovarHandleContrato(solicitacao);
        aprovarHandleTrajeto(solicitacao);

        solicitacao.setStatus(StatusSolicitacao.APROVADO);
        return repository.save(solicitacao);
    }

    private void aprovarHandleConversa(Solicitacao solicitacao) {
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
    }

    private void aprovarHandleContrato(Solicitacao solicitacao) {
        Optional<Contrato> contrato = contratoService.listarPorResponsavelIdAndMotoristaId(
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
            contratoService.criar(
                    ContratoMapper.toEntity(contratoRequest),
                    contratoRequest.getResponsavelId(),
                    contratoRequest.getMotoristaId(),
                    contratoRequest.getDependentesId()
            );
        } else {
            ContratoUpdate update = new ContratoUpdate(
                    contrato.get().getId(),
                    contrato.get().getDependentes().stream().map(Dependente::getId).toList(),
                    contrato.get().getDataInicio(),
                    contrato.get().getDataFim(),
                    solicitacao.getValor().doubleValue()
            );
            contratoService.atualizar(update);
        }
    }

    private void aprovarHandleTrajeto(Solicitacao solicitacao) {
        // Separa a String de diaSemana da solicitação
        // em uma lista convertida para o enum DiaSemana
        List<DiaSemana> diasSemana =
                Arrays.stream(solicitacao.getDiaSemana()
                        .split(" "))
                        .map(DiaSemana::valueOf)
                        .toList();

        // Separa a String de tipoTrajeto da solicitação
        // em uma lista convertida para o enum TipoTrajeto
        List<TipoTrajeto> tiposTrajetos =
                Arrays.stream(solicitacao.getTipo()
                        .split(" "))
                        .map(TipoTrajeto::valueOf)
                        .toList();

        // Para cada dia da semana
        diasSemana.forEach(diaSemana -> {

            // Para cada tipo de trajeto
            tiposTrajetos.forEach(tipoTrajeto -> {

                Trajeto trajeto;

                Optional<Trajeto> trajetoOpt = trajetoService.listarTrajetoPorTipoPeriodoDiaSemanaEscolaMotorista(
                        tipoTrajeto,
                        solicitacao.getPeriodo(),
                        diaSemana,
                        solicitacao.getEscola().getId(),
                        solicitacao.getMotorista().getId()
                );

                if (trajetoOpt.isEmpty()) {
                    TrajetoRequest request = new TrajetoRequest(
                            tipoTrajeto,
                            solicitacao.getPeriodo(),
                            diaSemana,
                            solicitacao.getEscola().getId(),
                            solicitacao.getMotorista().getId()
                    );

                    trajeto = trajetoService.criar(
                            TrajetoMapper.toEntity(request), request.getMotoristaId(), request.getEscolaId()
                    );
                } else {
                    trajeto = trajetoOpt.get();
                }

                aprovarHandleRota(solicitacao, trajeto);
            });
        });

    }

    private void aprovarHandleRota(Solicitacao solicitacao, Trajeto trajeto) {

        Optional<Rota> rota = rotaService.listarPorTrajetoDependenteEndereco(
                trajeto.getId(),
                solicitacao.getDependente().getId(),
                solicitacao.getEndereco().getId()
        );

        if (rota.isEmpty()) {
            rotaService.criar(trajeto, solicitacao.getDependente(), solicitacao.getEndereco());
        }
    }

}
