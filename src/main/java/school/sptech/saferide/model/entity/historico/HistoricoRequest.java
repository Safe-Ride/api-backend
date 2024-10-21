package school.sptech.saferide.model.entity.historico;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoricoRequest {
    private Integer trajetoId;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
}
