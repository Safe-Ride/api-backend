package saferide.sptech.apibackend.dto.mensagem;

import lombok.Data;
import saferide.sptech.apibackend.entity.Status;

@Data
public class MensagemRequest {

    private Integer chatId;
    private Integer usuarioId;
    private Integer dependenteId;
    private Status status;

}
