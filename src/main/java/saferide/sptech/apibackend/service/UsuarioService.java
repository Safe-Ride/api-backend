package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.controller.security.jwt.GerenciadorTokenJwt;
import saferide.sptech.apibackend.dto.usuario.UsuarioMapper;
import saferide.sptech.apibackend.dto.usuario.UsuarioRequest;
import saferide.sptech.apibackend.dto.usuario.UsuarioRequestUpdate;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.repository.UsuarioRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;
import saferide.sptech.apibackend.repository.EnderecoRepository;
import saferide.sptech.apibackend.service.autentication.UsuarioLoginDto;
import saferide.sptech.apibackend.service.autentication.UsuarioTokenDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository repository;
    private final DependenteRepository dependenteRepository;

    public Usuario criar(UsuarioRequest request) {
        Usuario entity = UsuarioMapper.toEntity(request);
        String senhaCriptografada = passwordEncoder.encode(entity.getSenha());
        entity.setSenha(senhaCriptografada);
        return repository.save(entity);
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return usuarios;
    }

    public Usuario listarPorId(int id) {
        Optional<Usuario> clienteOpt = repository.findById(id);
        if (clienteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return clienteOpt.get();
    }

    public Usuario atualizar(int id, UsuarioRequestUpdate request) {
        Optional<Usuario> clienteOpt = repository.findById(id);
        if (clienteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Usuario entity = UsuarioMapper.toEntityAtt(request,clienteOpt.get());
        return repository.save(entity);
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<Dependente> dependentes = dependenteRepository.findByResponsavelId(id);
        if (!dependentes.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(),usuarioLoginDto.getSenha());

        final  Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                repository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404,"Email do usuário não cadastrado", null)
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado,token);
    }

    public void logoff(String token) {
        gerenciadorTokenJwt.invalidateToken(token);
    }

}
