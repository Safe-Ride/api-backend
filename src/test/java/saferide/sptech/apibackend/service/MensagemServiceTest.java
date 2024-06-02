package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.repository.MensagemRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MensagemServiceTest {

    @InjectMocks
    private MensagemService service;

    @Mock
    private MensagemRepository repository;

    @Mock
    private HistoricoService historicoService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private DependenteService dependenteService;

    @Test
    @DisplayName("Deve retornar a mensagem salva")
    void testSalvarMensagem() {
        // ARRANGE
        Historico historico = new Historico();
        historico.setId(1);
        Dependente dependente = new Dependente();
        dependente.setId(1);
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setDependentes(List.of(dependente));

        Mensagem payload = new Mensagem();
        payload.setData(LocalDateTime.now());
        payload.setStatus(Status.NA_VAN);
        Mensagem response = new Mensagem();
        response.setId(1);
        response.setData(LocalDateTime.now());
        response.setStatus(Status.NA_VAN);
        response.setHistorico(historico);
        response.setUsuario(usuario);
        response.setDependente(dependente);

        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(historicoService.listarPorId(historico.getId())).thenReturn(historico);
        Mockito.when(usuarioService.listarPorId(usuario.getId())).thenReturn(usuario);
        Mockito.when(dependenteService.listarPorId(dependente.getId())).thenReturn(dependente);

        // ACT
        Mensagem save = service.criar(payload, historico.getId(), usuario.getId(), dependente.getId());

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getHistorico().getId(), save.getHistorico().getId());
        assertEquals(response.getUsuario().getId(), save.getUsuario().getId());
        assertEquals(response.getDependente().getId(), save.getDependente().getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

}
