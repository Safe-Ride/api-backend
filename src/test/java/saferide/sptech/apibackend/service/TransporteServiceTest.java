package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.TransporteRepository;

@ExtendWith(MockitoExtension.class)
public class TransporteServiceTest {

    @InjectMocks
    private TransporteService service;

    @Mock
    private TransporteRepository repository;

}
