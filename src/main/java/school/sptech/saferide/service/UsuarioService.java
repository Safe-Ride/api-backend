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
import school.sptech.saferide.model.entity.imagem.Imagem;
import school.sptech.saferide.model.entity.usuario.MotoristaListarClientes;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.entity.usuario.UsuarioMapper;
import school.sptech.saferide.model.exception.ConflictException;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.DependenteRepository;
import school.sptech.saferide.repository.UsuarioRepository;
import school.sptech.saferide.service.utils.S3Configure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private final S3Configure s3;

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

    public List<Usuario> listarMotoristasPorCliente(int responsavelId) {
        listarPorId(responsavelId);
        return repository.findMotoristasByResponsavelId(responsavelId);
    }

    public byte[] consultarFotoPerfilPorId(int id) {
        Usuario usuario = listarPorId(id);
        return s3.baixarArquivoS3(usuario.getImagem().getCaminho());
    }

    public void atualizarFotoPerfilPorId(int id, byte[] foto, String contentType) {
        Usuario usuario = listarPorId(id);

        String extensao = switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de imagem não suportado");
        };

        String nomeArquivo = UUID.randomUUID().toString() + extensao;

        s3.gravarArquivoS3(nomeArquivo, foto);

        Imagem imagem = new Imagem();
        imagem.setCaminho(nomeArquivo);
        usuario.setImagem(imagemService.criar(imagem));

        repository.save(usuario);
    }

    public Usuario atualizarNome(int id, String nome) {
        Usuario usuario = listarPorId(id);
        usuario.setNome(nome);
        return repository.save(usuario);
    }

    public Usuario atualizarEmail(int id, String email) {
        Usuario usuario = listarPorId(id);
        usuario.setEmail(email);
        return repository.save(usuario);
    }

    public Usuario atualizarCpf(int id, String cpf) {
        Usuario usuario = listarPorId(id);
        usuario.setCpf(cpf);
        return repository.save(usuario);
    }

    public Usuario atualizarTelefone(int id, String telefone) {
        Usuario usuario = listarPorId(id);
        usuario.setTelefone(telefone);
        return repository.save(usuario);
    }

    public Usuario atualizarDataNascimento(int id, String dataNascimento) {
        try {
            Usuario usuario = listarPorId(id);
            LocalDate novaDataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ISO_LOCAL_DATE);
            usuario.setDataNascimento(novaDataNascimento);
            return repository.save(usuario);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Formato de data inválido: " + e.getMessage());
        }
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

    public List<Dependente> listarStatusDependentesPorResponsavel(int id){
        List<Dependente> dependentes = dependenteRepository.findByResponsavelId(id);
        if (dependentes.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return dependentes;
    }
}
