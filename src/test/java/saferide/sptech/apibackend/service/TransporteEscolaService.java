package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.TransporteEscolaRepository;

@ExtendWith(MockitoExtension.class)
public class TransporteEscolaService {

    @InjectMocks
    private TransporteEscolaService service;

    @Mock
    private TransporteEscolaRepository repository;

}
