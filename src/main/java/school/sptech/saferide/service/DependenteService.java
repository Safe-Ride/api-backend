package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.TipoUsuario;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.model.exception.NotRemoveWithRelationshipsException;
import school.sptech.saferide.model.exception.TypeUserInvalidException;
import school.sptech.saferide.repository.DependenteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository repository;
    private final UsuarioService usuarioService;
    private final EscolaService escolaService;
    private final ConversaService conversaService;

    public Dependente criar(Dependente payload, int usuarioId, int escolaId) {
        Usuario responsavel = usuarioService.listarPorId(usuarioId);
        if (!responsavel.getTipo().equals(TipoUsuario.RESPONSAVEL)) throw new TypeUserInvalidException(TipoUsuario.MOTORISTA.name());
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

        conversaService.criar(
                new Conversa(),
                dependente.getResponsavel().getId(),
                motorista.getId());

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
}
