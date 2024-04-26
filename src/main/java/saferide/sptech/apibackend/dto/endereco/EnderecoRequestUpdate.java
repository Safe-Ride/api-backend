package saferide.sptech.apibackend.dto.endereco;

import lombok.Data;

@Data
public class EnderecoRequestUpdate {

    private Double latitude;
    private Double longitude;
    private String cep;
    private Integer numero;
    private String complemento;

}
