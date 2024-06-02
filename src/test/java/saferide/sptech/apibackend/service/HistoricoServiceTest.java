package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.repository.HistoricoRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HistoricoServiceTest {

    @InjectMocks
    private HistoricoService service;

    @Mock
    private HistoricoRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve retornar o historico salvo")
    void testSalvarHistorico() {
        // ARRANGE
        Usuario responsavel = new Usuario();
        responsavel.setId(1);
        responsavel.setTipo(TipoUsuario.RESPONSAVEL);
        Usuario motorista = new Usuario();
        motorista.setId(2);
        motorista.setTipo(TipoUsuario.MOTORISTA);

        Historico payload = new Historico();

        Historico response = new Historico();
        response.setId(1);
        response.setResponsavel(responsavel);
        response.setMotorista(motorista);

        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(usuarioService.listarPorId(1)).thenReturn(responsavel);
        Mockito.when(usuarioService.listarPorId(2)).thenReturn(motorista);

        // ACT
        Historico save = service.criar(payload, responsavel.getId(), motorista.getId());

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getResponsavel().getId(), save.getResponsavel().getId());
        assertEquals(TipoUsuario.RESPONSAVEL, save.getResponsavel().getTipo());
        assertEquals(response.getMotorista().getId(), save.getMotorista().getId());
        assertEquals(TipoUsuario.MOTORISTA, save.getMotorista().getTipo());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

}
