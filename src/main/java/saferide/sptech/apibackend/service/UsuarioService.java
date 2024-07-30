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
import saferide.sptech.apibackend.entity.Imagem;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.exception.ConflictException;
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
    private final ImagemService imagemService;

    public Usuario criar(Usuario payload) {
        if (!repository.findByEmail(payload.getEmail()).isEmpty()) throw new ConflictException("email");
        payload.setImagem(imagemService.listarPorId(1));
        String senhaCriptografada = passwordEncoder.encode(payload.getSenha());
        payload.setSenha(senhaCriptografada);
        return repository.save(payload);
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

    public Usuario listarPerfilPorId(int id) {
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

    public Usuario atualizarNome(int id, String alteracao) {
        listarPorId(id);
        repository.atualizarNome(id, alteracao);
        return repository.findById(id).get();
    }

    public Usuario atualizarEmail(int id, String alteracao) {
        listarPorId(id);
        repository.atualizarEmail(id, alteracao);
        return repository.findById(id).get();
    }

    public Usuario atualizarCpf(int id, String alteracao) {
        listarPorId(id);
        repository.atualizarCpf(id, alteracao);
        return repository.findById(id).get();
    }

    public Usuario atualizarTelefone(int id, String alteracao) {
        listarPorId(id);
        repository.atualizarTelefone(id, alteracao);
        return repository.findById(id).get();
    }

    public Usuario atualizarDataNascimento(int id, String alteracao) {
        listarPorId(id);
        repository.atualizarDataNascimento(id, alteracao);
        return repository.findById(id).get();
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
