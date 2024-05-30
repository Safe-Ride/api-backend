package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.DependenteRepository;

@ExtendWith(MockitoExtension.class)
public class DependenteServiceTest {

    @InjectMocks
    private DependenteService service;

    @Mock
    private DependenteRepository repository;

}
