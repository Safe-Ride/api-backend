package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.repository.TrajetoRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrajetoServiceTest {

    @InjectMocks
    private TrajetoService service;

    @Mock
    private TrajetoRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private EscolaService escolaService;

    @Test
    @DisplayName("Deve retornar o trajeto salvo")
    void testSalvarTrajeto() {
        // ARRANGE
        Usuario motorista = new Usuario();
        motorista.setId(1);
        Escola escola = new Escola();
        escola.setId(1);

        Trajeto payload = new Trajeto();
        payload.setDiaSemana(DiaSemana.SEGUNDA);
        payload.setTipo(TipoTrajeto.IDA);
        Trajeto response = new Trajeto();
        response.setId(1);
        response.setDiaSemana(DiaSemana.SEGUNDA);
        response.setTipo(TipoTrajeto.IDA);
        response.setMotorista(motorista);
        response.setEscola(escola);

        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(usuarioService.listarPorId(motorista.getId())).thenReturn(motorista);
        Mockito.when(escolaService.listarPorId(escola.getId())).thenReturn(escola);

        // ACT
        Trajeto save = service.criar(payload, motorista.getId(), escola.getId());

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getDiaSemana(), save.getDiaSemana());
        assertEquals(response.getTipo(), save.getTipo());
        assertEquals(response.getMotorista().getId(), save.getMotorista().getId());
        assertEquals(response.getEscola().getId(), save.getEscola().getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

    @Test
    @DisplayName("Retorna lista de trajeto vazia")
    void retornaListaDeTrajetoVazia() {
        // ARRANGE
        List<Trajeto> list = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(list);

        // ACT

        // ASSERT
        assertThrows(ResponseStatusException.class, () -> service.listar());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista com 3 trajetos")
    void deveRetornarListaCom3Trajetos() {
        // ARRANGE
        List<Trajeto> list = List.of(
                new Trajeto(),
                new Trajeto(),
                new Trajeto());
        Mockito.when(repository.findAll()).thenReturn(list);

        // ACT
        List<Trajeto> listReturn = service.listar();

        // ASSERT
        assertEquals(list.size(), listReturn.size());
        assertEquals(3, listReturn.size());
        assertFalse(listReturn.isEmpty());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

}
