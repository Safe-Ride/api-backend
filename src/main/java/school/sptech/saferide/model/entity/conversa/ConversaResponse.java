package school.sptech.saferide.model.entity.conversa;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import school.sptech.saferide.model.enums.StatusDependente;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConversaResponse {
    private Integer id;
    private Usuario responsavel;
    private Usuario motorista;
    private List<Mensagem> mensagems;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Usuario {
        private Integer id;
        private String nome;
        private String telefone;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Mensagem {
        private Integer id;
        private LocalDateTime data;
        private StatusDependente status;
        private Integer usuarioId;
        private Dependente dependente;

        @Data
        @Builder
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Dependente {
            private Integer id;
            private String nome;
        }
    }
}
