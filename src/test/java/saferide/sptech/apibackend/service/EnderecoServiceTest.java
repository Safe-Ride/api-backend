package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.EnderecoRepository;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService service;

    @Mock
    private EnderecoRepository repository;

}
