package school.sptech.saferide.model.entity.tempoReal;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.EnableMBeanExport;
import school.sptech.saferide.model.embeddable.TempoRealId;
import school.sptech.saferide.model.entity.usuario.Usuario;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempoReal {
    @EmbeddedId
    private TempoRealId id;

    private String latitude;
    private String longitude;

}
