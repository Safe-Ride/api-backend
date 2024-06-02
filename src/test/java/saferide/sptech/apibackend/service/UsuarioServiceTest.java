package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.entity.exception.ConflictException;
import saferide.sptech.apibackend.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private ImagemService imagemService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Deve retornar o usuario responsavel salvo")
    void testSalvarUsuarioResponsavel() {
        // ARRANGE
        Imagem imagem = new Imagem(1, "profile.png");
        Usuario payload = new Usuario(
                null,
                "teste",
                "teste@gmail.com",
                "123456",
                "123.123.123-99",
                "(11)91111-2222",
                LocalDate.parse("2000-01-01"),
                TipoUsuario.RESPONSAVEL,
                null,
                null,
                null,
                new Imagem(1, "profile.png"));
        Usuario response = new Usuario(
                1,
                "teste",
                "teste@gmail.com",
                "123456",
                "123.123.123-99",
                "(11)91111-2222",
                LocalDate.parse("2000-01-01"),
                TipoUsuario.RESPONSAVEL,
                null,
                null,
                null,
                imagem);
        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(imagemService.listarPorId(payload.getImagem().getId())).thenReturn(imagem);
        Mockito.when(passwordEncoder.encode(payload.getSenha())).thenReturn(payload.getSenha());

        // ACT
        Usuario save = service.criar(payload);

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getNome(), save.getNome());
        assertEquals(response.getEmail(), save.getEmail());
        assertEquals(response.getCpf(), save.getCpf());
        assertEquals(response.getTelefone(), save.getTelefone());
        assertEquals(response.getDataNascimento(), save.getDataNascimento());
        assertEquals(response.getTipo(), save.getTipo());
        assertEquals(response.getImagem().getId(), save.getImagem().getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

    @Test
    @DisplayName("Deve retornar o usuario motorista salvo")
    void testSalvarUsuarioMotorista() {
        // ARRANGE
        Imagem imagem = new Imagem(1, "profile.png");
        Usuario payload = new Usuario(
                null,
                "teste",
                "teste@gmail.com",
                "123456",
                "123.123.123-99",
                "(11)91111-2222",
                LocalDate.parse("2000-01-01"),
                TipoUsuario.RESPONSAVEL,
                null,
                null,
                null,
                new Imagem(1, "profile.png"));
        Usuario response = new Usuario(
                1,
                "teste",
                "teste@gmail.com",
                "123456",
                "123.123.123-99",
                "(11)91111-2222",
                LocalDate.parse("2000-01-01"),
                TipoUsuario.MOTORISTA,
                null,
                null,
                null,
                imagem);
        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(imagemService.listarPorId(payload.getImagem().getId())).thenReturn(imagem);
        Mockito.when(passwordEncoder.encode(payload.getSenha())).thenReturn(payload.getSenha());

        // ACT
        Usuario save = service.criar(payload);

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getNome(), save.getNome());
        assertEquals(response.getEmail(), save.getEmail());
        assertEquals(response.getCpf(), save.getCpf());
        assertEquals(response.getTelefone(), save.getTelefone());
        assertEquals(response.getDataNascimento(), save.getDataNascimento());
        assertEquals(response.getTipo(), save.getTipo());
        assertEquals(response.getImagem().getId(), save.getImagem().getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

    @Test
    @DisplayName("Deve retornar erro ao cadastrar um usuario com email duplicado na base")
    void testSalvarUsuarioComEmailDuplicado() {
        // ARRANGE
        Usuario response = new Usuario();
        Usuario payload = new Usuario();
        payload.setEmail("teste@gmail.com");
        Mockito.when(repository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(response));

        // ACT

        // ASSERT
        assertThrows(ConflictException.class, () -> service.criar(payload));
        Mockito.verify(repository, Mockito.times(1)).findByEmail("teste@gmail.com");
    }

    @Test
    @DisplayName("Retorna lista de usuarios vazia")
    void retornaListaVazia() {
        // ARRANGE
        List<Usuario> usuarios = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(usuarios);

        // ACT

        // ASSERT
        assertThrows(ResponseStatusException.class, () -> service.listar());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista com 3 usuarios")
    void deveRetornarListaComDependentes() {
        // ARRANGE
        List<Usuario> list = List.of(
                new Usuario(),
                new Usuario(),
                new Usuario());
        Mockito.when(repository.findAll()).thenReturn(list);

        // ACT
        List<Usuario> listReturn = service.listar();

        // ASSERT
        assertEquals(list.size(), listReturn.size());
        assertEquals(3, listReturn.size());
        assertFalse(listReturn.isEmpty());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

}
