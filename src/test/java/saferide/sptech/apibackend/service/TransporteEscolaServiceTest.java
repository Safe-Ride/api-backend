package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Transporte;
import saferide.sptech.apibackend.entity.TransporteEscola;
import saferide.sptech.apibackend.repository.TransporteEscolaRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransporteEscolaServiceTest {

    @InjectMocks
    private TransporteEscolaService service;

    @Mock
    private TransporteEscolaRepository repository;

    @Mock
    private TransporteService transporteService;

    @Mock
    private EscolaService escolaService;

    @Test
    @DisplayName("Deve retornar a associacao de transporte e escola salva")
    void testSalvarTransporteEscola() {
        // ARRANGE
        Transporte transporte = new Transporte();
        transporte.setId(1);
        Escola escola = new Escola();
        escola.setId(1);

        TransporteEscola payload = new TransporteEscola();
        TransporteEscola response = new TransporteEscola();
        response.setTransporte(transporte);
        response.setEscola(escola);

        Mockito.when(repository.save(payload)).thenReturn(response);
        Mockito.when(transporteService.listarPorId(transporte.getId())).thenReturn(transporte);
        Mockito.when(escolaService.listarPorId(escola.getId())).thenReturn(escola);

        // ACT
        TransporteEscola save = service.criar(payload, transporte.getId(), escola.getId());

        // ASSERT
        assertEquals(response.getId(), save.getId());
        assertEquals(response.getTransporte().getId(), save.getTransporte().getId());
        assertEquals(response.getEscola().getId(), save.getEscola().getId());
        Mockito.verify(repository, Mockito.times(1)).save(payload);
    }

}
