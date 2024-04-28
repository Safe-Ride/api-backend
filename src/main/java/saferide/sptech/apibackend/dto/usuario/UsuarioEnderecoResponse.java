package saferide.sptech.apibackend.dto.usuario;

import lombok.Data;

@Data
public class UsuarioEnderecoResponse {

    private Integer id;
    private Double latitude;
    private Double longitude;
    private String cep;
    private Integer numero;
    private String complemento;

}
