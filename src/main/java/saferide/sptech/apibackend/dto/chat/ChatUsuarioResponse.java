package saferide.sptech.apibackend.dto.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import saferide.sptech.apibackend.entity.Usuario;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatUsuarioResponse {

    private int id;
    private String nome;
    private String telefone;

}
