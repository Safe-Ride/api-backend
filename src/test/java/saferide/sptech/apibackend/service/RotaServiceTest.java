package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.RotaRepository;

@ExtendWith(MockitoExtension.class)
public class RotaServiceTest {

    @InjectMocks
    private RotaService service;

    @Mock
    private RotaRepository repository;

}
