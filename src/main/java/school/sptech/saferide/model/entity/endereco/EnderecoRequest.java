package school.sptech.saferide.model.entity.endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {
    private Double latitude;
    private Double longitude;
    private String cep;
    private Integer numero;
    private String complemento;
    private Integer usuarioId;
}
