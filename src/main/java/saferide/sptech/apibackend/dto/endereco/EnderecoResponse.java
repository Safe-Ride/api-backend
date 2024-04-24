package saferide.sptech.apibackend.dto.endereco;

import lombok.Getter;
import lombok.Setter;
import saferide.sptech.apibackend.entity.Usuario;

@Getter
@Setter
public class EnderecoResponse {
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String cep;
    private Integer numero;
    private String complemento;
    private Usuario usuario;

}
