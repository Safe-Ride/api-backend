package saferide.sptech.apibackend.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import saferide.sptech.apibackend.repository.MensagemRepository;

@ExtendWith(MockitoExtension.class)
public class MensagemServiceTest {

    @InjectMocks
    private MensagemService service;

    @Mock
    private MensagemRepository repository;

}
