package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.HistoricoRepository;

@ExtendWith(MockitoExtension.class)
public class HistoricoServiceTest {

    @InjectMocks
    private HistoricoService service;

    @Mock
    private HistoricoRepository repository;

}
