package saferide.sptech.apibackend.dto.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Usuario;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponse {

    private Integer id;
    private ChatUsuarioResponse responsavel;
    private ChatUsuarioResponse motorista;
    private List<ChatMensagemResponse> mensagems;

}
