package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioService usuarioService;

    public Endereco criar(Endereco payload, int usuarioId) {
        Usuario usuario = usuarioService.listarPorId(usuarioId);
        payload.setUsuario(usuario);
        return repository.save(payload);
    }

    public Endereco listarPorId(int id) {
        Optional<Endereco> enderecoOpt = repository.findById(id);
        if (enderecoOpt.isEmpty()) throw new NotFoundException("Endereço");
        return enderecoOpt.get();
    }

    public List<Endereco> listarPorUsuario(int usuarioId) {
        Usuario usuario = usuarioService.listarPorId(usuarioId);
        List<Endereco> enderecos = repository.findByUsuarioId(usuarioId);
        if (enderecos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return enderecos;
    }

    public Void remover(int id) {
        listarPorId(id);
        repository.deleteById(id);
        return null;
    }
}
