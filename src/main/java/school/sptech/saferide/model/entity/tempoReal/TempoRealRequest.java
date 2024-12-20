package school.sptech.saferide.model.entity.tempoReal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempoRealRequest {
    private Double latitude;
    private Double longitude;
}
