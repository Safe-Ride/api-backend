package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.chat.ChatRequest;
import saferide.sptech.apibackend.dto.dependente.DependenteMapper;
import saferide.sptech.apibackend.dto.dependente.DependenteRequest;
import saferide.sptech.apibackend.dto.dependente.DependenteRequestUpdate;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.TipoCliente;
import saferide.sptech.apibackend.repository.DependenteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository repository;
    private final UsuarioService usuarioService;
    private final EscolaService escolaService;
    private final ChatService chatService;

    public Dependente criar(DependenteRequest request) {
        Usuario responsavel = usuarioService.listarPorId(request.getResponsavelId());
        if (!responsavel.getTipo().equals(TipoCliente.RESPONSAVEL)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Escola escola = escolaService.listarPorId(request.getEscolaId());
        Dependente entity = DependenteMapper.toEntity(request, responsavel, escola);
        return repository.save(entity);
    }

    public List<Dependente> listar() {
        List<Dependente> dependentes = repository.findAll();
        if (dependentes.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return dependentes;
    }

    public Dependente listarPorId(int id) {
        Optional<Dependente> dependenteOpt = repository.findById(id);
        if (dependenteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return dependenteOpt.get();
    }

    public Dependente atualizar(int id, DependenteRequestUpdate request) {
        Optional<Dependente> dependenteOpt = repository.findById(id);
        if (dependenteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Dependente entity = DependenteMapper.toEntityAtt(request,dependenteOpt.get());
        return repository.save(entity);
    }

    public Dependente vincularMotorista(int dependenteId, int motoristaId) {

        Usuario motorista = usuarioService.listarPorId(motoristaId);
        if (motorista.getTipo().equals(TipoCliente.MOTORISTA)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<Dependente> dependenteOpt = repository.findById(dependenteId);
        if (dependenteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Dependente entity = dependenteOpt.get();
        entity.setMotorista(motorista);

        chatService.criar(new ChatRequest(dependenteOpt.get().getResponsavel().getId(), motorista.getId()));

        return repository.save(entity);
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }

}
