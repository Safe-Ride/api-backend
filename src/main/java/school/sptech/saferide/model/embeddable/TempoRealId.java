package school.sptech.saferide.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TempoRealId {
    private Integer idMotorista;
    private LocalDateTime data;
}
