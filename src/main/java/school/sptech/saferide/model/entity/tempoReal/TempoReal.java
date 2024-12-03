package school.sptech.saferide.model.entity.tempoReal;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.embeddable.TempoRealId;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempoReal {
    @EmbeddedId
    private TempoRealId id;

    private Double latitude;
    private Double longitude;

}
