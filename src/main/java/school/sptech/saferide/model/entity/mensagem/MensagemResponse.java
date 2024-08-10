package school.sptech.saferide.model.entity.mensagem;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import school.sptech.saferide.model.enums.StatusDependente;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MensagemResponse {
    private Integer id;
    private LocalDateTime data;
    private StatusDependente status;
    private Historico historico;
    private Usuario usuario;
    private Dependente dependente;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Historico {
        private Integer id;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Usuario {
        private Integer id;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Dependente {
        private Integer id;
        private String nome;
    }
}
