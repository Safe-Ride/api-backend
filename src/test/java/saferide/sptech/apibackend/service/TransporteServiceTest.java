package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.entity.Imagem;
import saferide.sptech.apibackend.entity.Transporte;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.TransporteRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransporteServiceTest {

    @InjectMocks
    private TransporteService service;

    @Mock
    private TransporteRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Deve retornar a imagem salva")
    void testSalvarImagem() {
        // ARRANGE
        Usuario motorista = new Usuario();
        motorista.setId(1);

        Transporte payload = new Transporte();
        payload.setPlaca("a");
        payload.setCnpj("b");
        payload.setCnh("c");
        payload.setCrm("d");
        payload.setCrmc("e");
        Transporte response = new Transporte();
        response.setId(1);
        response.setPlaca("a");
        response.setCnpj("b");
        response.setCnh("c");
        response.setCrm("d");
        response.setCrmc("e");
        response.setUsuario(motorista);

        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(usuarioService.listarPorId(motorista.getId())).thenReturn(motorista);

        // ACT
        Transporte save = service.criar(payload, motorista.getId());

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getUsuario().getId(), save.getUsuario().getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

    @Test
    @DisplayName("Retorna lista de imagem vazia")
    void retornaListaDeImagemVazia() {
        // ARRANGE
        List<Transporte> list = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(list);

        // ACT

        // ASSERT
        assertThrows(ResponseStatusException.class, () -> service.listar());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista com 3 imagens")
    void deveRetornarListaCom3Imagens() {
        // ARRANGE
        List<Transporte> list = List.of(
                new Transporte(),
                new Transporte(),
                new Transporte());
        Mockito.when(repository.findAll()).thenReturn(list);

        // ACT
        List<Transporte> listReturn = service.listar();

        // ASSERT
        assertEquals(list.size(), listReturn.size());
        assertEquals(3, listReturn.size());
        assertFalse(listReturn.isEmpty());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

}
