package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.tempoReal.TempoReal;
import school.sptech.saferide.model.entity.tempoReal.TempoRealMapper;
import school.sptech.saferide.model.entity.tempoReal.TempoRealRequest;
import school.sptech.saferide.model.entity.trajeto.TrajetoResponse;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.TipoUsuario;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.model.exception.TypeUserInvalidException;
import school.sptech.saferide.repository.TempoRealRepository;
import school.sptech.saferide.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TempoRealSersvice {
    private final UsuarioRepository usuarioRepository;
    private final TempoRealRepository tempoRealRepository;

    public Usuario motorista(Integer idMotorista) {
        Usuario motorista = usuarioRepository.findById(idMotorista).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (motorista.getTipo().equals(TipoUsuario.RESPONSAVEL)) throw new TypeUserInvalidException(TipoUsuario.RESPONSAVEL.name());
        return motorista;
    }


    public void save(TempoRealRequest tempoRealRequest, Integer idMotorista) {
        Usuario motorista = motorista(idMotorista);
        if (tempoRealRequest.getData() == null) {
            tempoRealRequest.setData(LocalDateTime.now());
        }

        TempoReal tempoReal = TempoRealMapper.toEntity(tempoRealRequest, motorista);
        tempoRealRepository.save(tempoReal);
    }

    public TempoReal findById(Integer motoristaId) {
        motorista(motoristaId);

        Optional<TempoReal> tempoReal = tempoRealRepository.findLatestByMotorista(motoristaId);
        if (tempoReal.isEmpty()) throw new NotFoundException("Tempo Real");
        return tempoReal.get();
    }
}
