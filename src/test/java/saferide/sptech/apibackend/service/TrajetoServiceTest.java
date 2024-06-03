package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.TrajetoRepository;

@ExtendWith(MockitoExtension.class)
public class TrajetoServiceTest {

    @InjectMocks
    private TrajetoService service;

    @Mock
    private TrajetoRepository repository;

}
