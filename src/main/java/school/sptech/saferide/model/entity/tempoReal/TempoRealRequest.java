package school.sptech.saferide.model.entity.tempoReal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TempoRealRequest {
    private String latitude;
    private String longitude;
    private LocalDateTime data;
}
