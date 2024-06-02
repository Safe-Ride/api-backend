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
import saferide.sptech.apibackend.repository.ImagemRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ImagemServiceTest {

    @InjectMocks
    private ImagemService service;

    @Mock
    private ImagemRepository repository;

    @Test
    @DisplayName("Deve retornar a imagem salva")
    void testSalvarImagem() {
        // ARRANGE
        Imagem payload = new Imagem();
        payload.setCaminho("profile.png");
        Imagem response = new Imagem();
        response.setId(1);
        response.setCaminho("profile.png");

        Mockito.when(repository.save(payload)).thenReturn(response);

        // ACT
        Imagem save = service.criar(payload);

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getCaminho(), save.getCaminho());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

    @Test
    @DisplayName("Retorna lista de imagem vazia")
    void retornaListaDeImagemVazia() {
        // ARRANGE
        List<Imagem> list = new ArrayList<>();
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
        List<Imagem> list = List.of(
                new Imagem(),
                new Imagem(),
                new Imagem());
        Mockito.when(repository.findAll()).thenReturn(list);

        // ACT
        List<Imagem> listReturn = service.listar();

        // ASSERT
        assertEquals(list.size(), listReturn.size());
        assertEquals(3, listReturn.size());
        assertFalse(listReturn.isEmpty());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

}
