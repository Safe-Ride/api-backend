package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.dependente.DependenteMapper;
import saferide.sptech.apibackend.dto.dependente.DependenteRequest;
import saferide.sptech.apibackend.dto.dependente.DependenteRequestUpdate;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.TipoCliente;
import saferide.sptech.apibackend.repository.UsuarioRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DependenteService {

    private final DependenteRepository dependenteRepository;
    private final UsuarioRepository usuarioRepository;

    public Dependente criar(DependenteRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(request.getUsuarioId());
        if (!usuarioOpt.get().getTipo().equals(TipoCliente.RESPONSAVEL)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (usuarioOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Dependente entity = DependenteMapper.toEntity(request, usuarioOpt.get());
        Dependente saveDependente = dependenteRepository.save(entity);
        return saveDependente;
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
        Dependente saveDependente = dependenteRepository.save(entity);
        return saveDependente;
    }

    public Void remover(int id) {
        if (!dependenteRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        dependenteRepository.deleteById(id);
        return null;
    }
}
