package saferide.sptech.apibackend.dto.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponse {

    private Integer id;
    private ChatUsuarioResponse responsavel;
    private ChatUsuarioResponse motorista;
    private List<ChatMensagemResponse> mensagems;

}
