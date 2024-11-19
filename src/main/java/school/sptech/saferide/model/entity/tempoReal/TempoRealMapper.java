package school.sptech.saferide.model.entity.tempoReal;

import org.springframework.stereotype.Component;
import school.sptech.saferide.model.embeddable.TempoRealId;
import school.sptech.saferide.model.entity.usuario.Usuario;

import java.time.LocalDateTime;

@Component
public class TempoRealMapper {
    public static TempoRealRequest toDto(TempoReal tempoReal) {
        if (tempoReal == null) {
            return null;
        }
        LocalDateTime date = LocalDateTime.now();
        return TempoRealRequest.builder()
                .latitude(tempoReal.getLatitude())
                .longitude(tempoReal.getLongitude())
                .data(tempoReal.getId().getData())
                .build();
    }

    public static TempoReal toEntity(TempoRealRequest tempoRealRequest, Usuario motorista) {
        if (tempoRealRequest == null) {
            return null;
        }
        LocalDateTime date = LocalDateTime.now();
        TempoRealId id = new TempoRealId(motorista.getId(), date);
        return TempoReal.builder()
                .id(id)
                .latitude(tempoRealRequest.getLatitude())
                .longitude(tempoRealRequest.getLongitude())
                .build();
    }
}
