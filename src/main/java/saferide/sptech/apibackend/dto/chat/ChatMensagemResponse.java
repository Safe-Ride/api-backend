package saferide.sptech.apibackend.dto.chat;

import lombok.Data;
import saferide.sptech.apibackend.entity.Status;

import java.time.LocalDateTime;

@Data
public class ChatMensagemResponse {

    private Integer id;
    private LocalDateTime data;
    private Status status;
    private Integer usuarioId;
    private ChatDependenteResponse dependente;

}
