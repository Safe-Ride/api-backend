package saferide.sptech.apibackend.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Usuario;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {

    private Integer responsavelId;
    private Integer motoristaId;

}
