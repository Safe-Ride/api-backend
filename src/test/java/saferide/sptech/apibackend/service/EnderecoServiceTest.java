package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.EnderecoRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService service;

    @Mock
    private EnderecoRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve retornar o endereco salvo")
    void testSalvarEndereco() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        Endereco payload = new Endereco();
        payload.setCep("01414001");
        payload.setNumero(595);
        payload.setComplemento("sala 1A");

        Endereco response = new Endereco();
        response.setId(1);
        response.setCep("01414001");
        response.setNumero(595);
        response.setComplemento("sala 1A");
        response.setUsuario(usuario);

        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(usuarioService.listarPorId(1)).thenReturn(usuario);

        // ACT
        Endereco save = service.criar(payload, usuario.getId());

        // ASSERT
        assertEquals(response.getId(), save.getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

}
