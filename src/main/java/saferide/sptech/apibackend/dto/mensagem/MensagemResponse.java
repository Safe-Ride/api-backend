package saferide.sptech.apibackend.dto.mensagem;

import lombok.Data;
import saferide.sptech.apibackend.entity.Status;

import java.time.LocalDateTime;

@Data
public class MensagemResponse {

    private Integer id;
    private LocalDateTime data;
    private Status status;
    private MensagemChatResponse chat;
    private MensagemUsuarioResponse usuario;
    private MensagemDependenteResponse dependente;
}
