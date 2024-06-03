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
import saferide.sptech.apibackend.repository.EscolaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EscolaServiceTest {

    @InjectMocks
    private EscolaService service;

    @Mock
    private EscolaRepository repository;

    @Mock
    private EnderecoService enderecoService;

    @Test
    @DisplayName("Deve retornar a escola salva")
    void testSalvarEscola() {
        // ARRANGE
        Endereco endereco = new Endereco();
        endereco.setId(1);

        Escola payload = new Escola();
        payload.setNome("SPTech");
        Escola response = new Escola();
        response.setId(1);
        response.setNome("SPTech");
        response.setEndereco(endereco);

        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(enderecoService.listarPorId(1)).thenReturn(endereco);

        // ACT
        Escola save = service.criar(payload, endereco.getId());

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getNome(), save.getNome());
        assertEquals(response.getEndereco().getId(), save.getEndereco().getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

    @Test
    @DisplayName("Retorna lista de escola vazia")
    void retornaListaDeEscolaVazia() {
        // ARRANGE
        List<Escola> list = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(list);

        // ACT

        // ASSERT
        assertThrows(ResponseStatusException.class, () -> service.listar());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista com 3 escolas")
    void deveRetornarListaCom3Escolas() {
        // ARRANGE
        List<Escola> list = List.of(
                new Escola(),
                new Escola(),
                new Escola());
        Mockito.when(repository.findAll()).thenReturn(list);

        // ACT
        List<Escola> listReturn = service.listar();

        // ASSERT
        assertEquals(list.size(), listReturn.size());
        assertEquals(3, listReturn.size());
        assertFalse(listReturn.isEmpty());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

}
