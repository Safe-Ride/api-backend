package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.solicitacao.Solicitacao;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository repository;
    private final UsuarioService usuarioService;
    private final EscolaService escolaService;
    private final EnderecoService enderecoService;
    private final DependenteService dependenteService;

    public Solicitacao criar(
            Solicitacao solicitacao,
            int motoristaId,
            int responsavelId,
            int escolaId,
            int enderecoId,
            int dependenteId) {
        Usuario motorista = usuarioService.listarPorId(motoristaId);
        Usuario responsavel = usuarioService.listarPorId(responsavelId);
        Escola escola = escolaService.listarPorId(escolaId);
        Endereco endereco = enderecoService.listarPorId(enderecoId);
        Dependente dependente = dependenteService.listarPorId(dependenteId);
        solicitacao.setMotorista(motorista);
        solicitacao.setResponsavel(responsavel);
        solicitacao.setEscola(escola);
        solicitacao.setEndereco(endereco);
        solicitacao.setDependente(dependente);
        return repository.save(solicitacao);
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

}
