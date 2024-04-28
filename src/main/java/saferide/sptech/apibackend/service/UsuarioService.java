package saferide.sptech.apibackend.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.usuario.UsuarioMapper;
import saferide.sptech.apibackend.dto.usuario.UsuarioRequest;
import saferide.sptech.apibackend.dto.usuario.UsuarioRequestUpdate;
import saferide.sptech.apibackend.dto.usuario.UsuarioResponse;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.repository.UsuarioRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;
import saferide.sptech.apibackend.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DependenteRepository dependenteRepository;
    private final EnderecoRepository enderecoRepository;

    public Usuario criar(UsuarioRequest request) {
        Usuario entity = UsuarioMapper.toEntity(request);
        Usuario saveUsuario = usuarioRepository.save(entity);
        return saveUsuario;
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return usuarios;
    }

    public Usuario listarPorId(int id) {
        Optional<Usuario> clienteOpt = usuarioRepository.findById(id);
        if (clienteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return clienteOpt.get();
    }

    public Usuario atualizar(int id, UsuarioRequestUpdate request) {
        Optional<Usuario> clienteOpt = usuarioRepository.findById(id);
        if (clienteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Usuario entity = UsuarioMapper.toEntityAtt(request,clienteOpt.get());
        Usuario saveUsuario = usuarioRepository.save(entity);
        return saveUsuario;
    }

    public Void remover(int id) {
        if (!usuarioRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<Dependente> dependentes = dependenteRepository.findByUsuarioId(id);
        if (!dependentes.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        usuarioRepository.deleteById(id);
        return null;
    }
}
