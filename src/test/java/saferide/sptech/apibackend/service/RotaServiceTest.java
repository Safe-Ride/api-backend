package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.rota.RotaResponse;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.repository.RotaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RotaServiceTest {

    @InjectMocks
    private RotaService service;

    @Mock
    private RotaRepository repository;

    @Mock
    private TrajetoService trajetoService;

    @Mock
    private DependenteService dependenteService;

    @Mock
    private EnderecoService enderecoService;

    @Test
    @DisplayName("Deve retornar a rota salva")
    void testSalvarRota() {
        // ARRANGE
        Trajeto trajeto = new Trajeto();
        trajeto.setId(1);
        Dependente dependente = new Dependente();
        dependente.setId(1);
        Endereco endereco = new Endereco();
        endereco.setId(1);

        Rota payload = new Rota();
        payload.setTrajeto(trajeto);
        payload.setDependente(dependente);
        payload.setEndereco(endereco);
        Rota response = new Rota();
        response.setTrajeto(trajeto);
        response.setDependente(dependente);
        response.setEndereco(endereco);

        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(trajetoService.listarPorId(trajeto.getId())).thenReturn(trajeto);
        Mockito.when(dependenteService.listarPorId(dependente.getId())).thenReturn(dependente);
        Mockito.when(enderecoService.listarPorId(endereco.getId())).thenReturn(endereco);

        // ACT
        Rota save = service.criar(payload, trajeto.getId(), dependente.getId(), endereco.getId());

        // ASSERT
        assertEquals(response.getTrajeto().getId(), save.getTrajeto().getId());
        assertEquals(response.getDependente().getId(), save.getDependente().getId());
        assertEquals(response.getEndereco().getId(), save.getEndereco().getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

}
