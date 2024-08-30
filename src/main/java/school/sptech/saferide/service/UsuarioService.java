package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.controller.security.jwt.GerenciadorTokenJwt;
import school.sptech.saferide.model.autentication.UsuarioLoginDto;
import school.sptech.saferide.model.autentication.UsuarioTokenDto;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.dependente.DependenteResponse;
import school.sptech.saferide.model.entity.usuario.MotoristaListarClientes;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.entity.usuario.UsuarioMapper;
import school.sptech.saferide.model.exception.ConflictException;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.DependenteRepository;
import school.sptech.saferide.repository.UsuarioRepository;

import java.util.*;

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
        if (repository.findByEmail(payload.getEmail()).isPresent()) throw new ConflictException(payload.getEmail());
        payload.setImagem(imagemService.listarPorId(1));
        String senhaCriptografada = passwordEncoder.encode(payload.getSenha());
        payload.setSenha(senhaCriptografada);
        return repository.save(payload);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(),usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                repository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404,"Email do usuário não cadastrado", null)
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.login(usuarioAutenticado,token);
    }

    public Usuario listarPorId(int id) {
        Optional<Usuario> clienteOpt = repository.findById(id);
        if (clienteOpt.isEmpty()) throw new NotFoundException("Usuario");
        return clienteOpt.get();
    }

    public List<MotoristaListarClientes> listarResponsaveisPorMotorista(int motoristaId) {
        List<Object[]> resultados = repository.findResponsaveisByMotoristaId(motoristaId);

        List<MotoristaListarClientes> responsaveis = new ArrayList<>();
        for (Object[] resultado : resultados) {
            int id = (Integer) resultado[0];
            String nome = (String) resultado[1];
            String foto = (String) resultado[2];
            responsaveis.add(new MotoristaListarClientes(id, nome, foto));
        }
        return responsaveis;
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

    public Void remover(int id) {
        listarPorId(id);
        List<Dependente> dependentes = dependenteRepository.findByResponsavelId(id);
        if (!dependentes.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }

    public List<Dependente> listarDependentesPorResponsavel(int id) {
        List<Dependente> dependentes = dependenteRepository.findByResponsavelId(id);
        if (dependentes.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return dependentes;
    }
}
