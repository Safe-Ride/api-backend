package school.sptech.saferide.model.entity.historico;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoricoResponse {
    private Integer id;
    private Trajeto trajeto;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Trajeto {
        private Integer id;
    }

}
