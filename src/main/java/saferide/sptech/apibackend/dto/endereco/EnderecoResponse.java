package saferide.sptech.apibackend.dto.endereco;

import lombok.Data;

@Data
public class EnderecoResponse {

    private Integer id;
    private Double latitude;
    private Double longitude;
    private String cep;
    private Integer numero;
    private String complemento;
    private EnderecoUsuarioResponse usuario;

}
