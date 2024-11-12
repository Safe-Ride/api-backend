package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.transporte.Transporte;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.TransporteRepository;

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

    public Transporte listarPorId(int id) {
        Optional<Transporte> transporteOpt = repository.findById(id);
        if (transporteOpt.isEmpty()) throw new NotFoundException("Transporte");
        return transporteOpt.get();
    }

    public List<Transporte> listarPorMotoristaId(int id) {
        List<Transporte> transportes = repository.findByUsuarioId(id);
        if (transportes.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return transportes;
    }

    public Void remover(int id) {
        listarPorId(id);
        repository.deleteById(id);
        return null;
    }
}
