package school.sptech.saferide.model.entity.tempoReal;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TempoRealResponse {
    private Double latitude;
    private Double longitude;
    private LocalDateTime data;
}
