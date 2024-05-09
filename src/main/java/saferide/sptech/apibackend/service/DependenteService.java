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
import saferide.sptech.apibackend.repository.EscolaRepository;
import saferide.sptech.apibackend.repository.UsuarioRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository dependenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final EscolaRepository escolaRepository;
    private final ChatService chatService;

    public Dependente criar(DependenteRequest request) {
        Optional<Usuario> responsavelOpt = usuarioRepository.findById(request.getResponsavelId());
//        if (responsavelOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        if (!responsavelOpt.get().getTipo().equals(TipoCliente.RESPONSAVEL)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Optional<Escola> escolaOpt = escolaRepository.findById(request.getEscolaId());
        if (escolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Dependente entity = DependenteMapper.toEntity(request, responsavelOpt.get(), escolaOpt.get());
        return dependenteRepository.save(entity);
    }

    public List<Dependente> listar() {
        List<Dependente> dependentes = dependenteRepository.findAll();
        if (dependentes.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return dependentes;
    }

    public Dependente listarPorId(int id) {
        Optional<Dependente> dependenteOpt = dependenteRepository.findById(id);
        if (dependenteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return dependenteOpt.get();
    }

    public Dependente atualizar(int id, DependenteRequestUpdate request) {
        Optional<Dependente> dependenteOpt = dependenteRepository.findById(id);
        if (dependenteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Dependente entity = DependenteMapper.toEntityAtt(request,dependenteOpt.get());
        return dependenteRepository.save(entity);
    }

    public Dependente vincularMotorista(int dependenteId, int motoristaId) {

        Optional<Usuario> motoristaOpt = usuarioRepository.findById(motoristaId);
        if (motoristaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (!motoristaOpt.get().getTipo().equals(TipoCliente.MOTORISTA)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<Dependente> dependenteOpt = dependenteRepository.findById(dependenteId);
        if (dependenteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Dependente entity = dependenteOpt.get();
        entity.setMotorista(motoristaOpt.get());

        chatService.criar(new ChatRequest(dependenteOpt.get().getResponsavel().getId(), motoristaOpt.get().getId()));

        return dependenteRepository.save(entity);
    }

    public Void remover(int id) {
        if (!dependenteRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        dependenteRepository.deleteById(id);
        return null;
    }
}
