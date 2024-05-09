package saferide.sptech.apibackend.dto.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatUsuarioResponse {

    private int id;
    private String nome;
    private String telefone;

}
