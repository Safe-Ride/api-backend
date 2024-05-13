package saferide.sptech.apibackend.dto.mensagem;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import saferide.sptech.apibackend.entity.Status;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MensagemResponse {

    private Integer id;
    private LocalDateTime data;
    private Status status;
    private Chat chat;
    private Usuario usuario;
    private Dependente dependente;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Chat {

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
