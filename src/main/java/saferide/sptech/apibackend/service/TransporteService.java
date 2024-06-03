package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.transporte.TransporteMapper;
import saferide.sptech.apibackend.dto.transporte.TransporteRequestUpdate;
import saferide.sptech.apibackend.entity.Transporte;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.TransporteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransporteService {

    private final TransporteRepository repository;
    private final UsuarioService usuarioService;

    public Transporte criar(
            Transporte payload,
            int usuarioId) {
        Usuario usuario = usuarioService.listarPorId(usuarioId);
        payload.setUsuario(usuario);
        return repository.save(payload);
    }

    public List<Transporte> listar() {
        List<Transporte> transportes = repository.findAll();
        if (transportes.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return transportes;
    }

    public Transporte listarPorId(int id) {
        Optional<Transporte> transporteOpt = repository.findById(id);
        if (transporteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return transporteOpt.get();
    }

    public Transporte atualizar(int id, TransporteRequestUpdate request) {
        Optional<Transporte> transporteOpt = repository.findById(id);
        if (transporteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Transporte entity = TransporteMapper.toEntityAtt(request, transporteOpt.get());
        return repository.save(entity);
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }
}
