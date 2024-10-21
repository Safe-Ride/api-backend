package school.sptech.saferide.model.entity.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteListarMotoristas {
    private int id;
    private String nome;
    private String foto;
}
