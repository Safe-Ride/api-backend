package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.TipoUsuario;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.DependenteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DependenteServiceTest {

    @InjectMocks
    private DependenteService service;

    @Mock
    private DependenteRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private EscolaService escolaService;

    @Test
    @DisplayName("Deve retornar lista com 3 dependentes")
    void deveRetornarListaComDependentes() {
        // ARRANGE
        Usuario usuario = new Usuario();
        Escola escola = new Escola();
        Usuario motorista = new Usuario();
        List<Dependente> dependentes = List.of(
                new Dependente(1, "Dependente 1", LocalDate.now(), "3° ano", usuario, escola, motorista),
                new Dependente(1, "Dependente 1", LocalDate.now(), "3° ano", usuario, escola, motorista),
                new Dependente(1, "Dependente 1", LocalDate.now(), "3° ano", usuario, escola, motorista)
        );
        Mockito.when(repository.findAll()).thenReturn(dependentes);

        // ACT
        List<Dependente> dependentesReturn = service.listar();

        // ASSERT
        assertEquals(dependentes.size(), dependentesReturn.size());
        assertEquals(3, dependentesReturn.size());
        assertFalse(dependentesReturn.isEmpty());
        assertEquals(dependentes.get(0).getNome(), dependentesReturn.get(0).getNome());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Retorna lista vazia")
    void retornaListaVazia() {
        // ARRANGE
        List<Dependente> dependentes = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(dependentes);

        // ACT

        // ASSERT
        assertThrows(ResponseStatusException.class, () -> service.listar());
        Mockito.verify(repository, Mockito.times(1)).findAll();

    }

    @Test
    @DisplayName("Deve retornar o objeto salvo")
    void testSalvaProduto() {
        // ARRANGE
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setTipo(TipoUsuario.RESPONSAVEL);
        Escola escola = new Escola();
        escola.setId(1);
        Usuario motorista = new Usuario();
        Dependente dependenteNovo = new Dependente(null, "Dependente 1", LocalDate.now(), "3° ano", usuario, escola, motorista);
        Dependente dependente = new Dependente(1, "Dependente 1", LocalDate.now(), "3° ano", usuario, escola, motorista);
        Mockito.when(repository.save(dependenteNovo)).thenReturn(dependente);
        Mockito.when(usuarioService.listarPorId(1)).thenReturn(usuario);
        Mockito.when(escolaService.listarPorId(1)).thenReturn(escola);

        // ACT
        Dependente dependenteSalvo = service.criar(dependenteNovo,usuario.getId(), escola.getId());

        // ASSERT
        assertEquals(dependente.getId(), dependenteSalvo.getId());
        Mockito.verify(repository, Mockito.times(1)).save(dependenteNovo);
    }

}
