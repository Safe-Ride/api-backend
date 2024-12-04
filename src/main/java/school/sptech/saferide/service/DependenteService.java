package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.contrato.Contrato;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.dependente.DependentePerfilResponse;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.TipoUsuario;
import school.sptech.saferide.model.exception.DependentBirthBeforeUserException;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.model.exception.NotRemoveWithRelationshipsException;
import school.sptech.saferide.model.exception.TypeUserInvalidException;
import school.sptech.saferide.repository.DependenteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository repository;
    private final UsuarioService usuarioService;
    private final EscolaService escolaService;

    public Dependente criar(Dependente payload, int usuarioId, int escolaId) {
        Usuario responsavel = usuarioService.listarPorId(usuarioId);
        if (!responsavel.getTipo().equals(TipoUsuario.RESPONSAVEL)) throw new TypeUserInvalidException(TipoUsuario.MOTORISTA.name());
        if (payload.getDataNascimento().isBefore(responsavel.getDataNascimento())) throw new DependentBirthBeforeUserException();
        Escola escola = escolaService.listarPorId(escolaId);
        payload.setResponsavel(responsavel);
        payload.setEscola(escola);
        return repository.save(payload);
    }

    public Dependente listarPorId(int id) {
        Optional<Dependente> dependenteOpt = repository.findById(id);
        if (dependenteOpt.isEmpty()) throw new NotFoundException("Dependente");
        return dependenteOpt.get();
    }

    public Dependente vincularMotorista(int dependenteId, int motoristaId) {
        Usuario motorista = usuarioService.listarPorId(motoristaId);
        if (!motorista.getTipo().equals(TipoUsuario.MOTORISTA)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Dependente dependente = listarPorId(dependenteId);
        dependente.setMotorista(motorista);

        return repository.save(dependente);
    }

    public void atualizarContrato(Dependente dependente, Contrato contrato) {
        dependente.setContrato(contrato);
        repository.save(dependente);
    }

    public DependentePerfilResponse listarPerfilPorId(int id) {
        listarPorId(id);
        return repository.listarPerfilPorId(id);
    }

    public Dependente atualizarNome(int id, String alteracao) {
        Dependente dependente = listarPorId(id);
        dependente.setNome(alteracao);
        return repository.save(dependente);
    }

    public Dependente atualizarDataNascimento(int id, String alteracao) {
        Dependente dependente = listarPorId(id);
        dependente.setDataNascimento(LocalDate.parse(alteracao));
        return repository.save(dependente);
    }

    public Dependente atualizarSerie(int id, String alteracao) {
        Dependente dependente = listarPorId(id);
        dependente.setSerie(alteracao);
        return repository.save(dependente);
    }

    public Dependente atualizarEscola(int id, String alteracao) {
        Dependente dependente = listarPorId(id);
        Escola escola = escolaService.listarPorNome(alteracao);
        dependente.setEscola(escola);
        return repository.save(dependente);
    }

    public Void remover(int id) {
        Dependente dependente = listarPorId(id);
        if (repository.findByIdAndMotoristaId(
                id,
                dependente.getMotorista().getId()
        ).isPresent()) throw new NotRemoveWithRelationshipsException("Dependente", "Motorista");
        repository.deleteById(id);
        return null;
    }

    public List<Dependente> listarPorIdMotoristaEIdTrajetoEIdEscolaOndeNaoExiste(Integer motoristaId, Integer trajetoId, Integer escolaId) {
        return repository.findByMotoristaAndTrajetoAndEscolaWhereNotExists(motoristaId, trajetoId, escolaId);
    }

}
