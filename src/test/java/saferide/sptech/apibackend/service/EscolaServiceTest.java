package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.EscolaRepository;

@ExtendWith(MockitoExtension.class)
public class EscolaServiceTest {

    @InjectMocks
    private EscolaService service;

    @Mock
    private EscolaRepository repository;

}
