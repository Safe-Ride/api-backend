package saferide.sptech.apibackend.dto.escola;

import lombok.Data;

@Data
public class EscolaEnderecoResponse {

    private Integer id;
    private Double latitude;
    private Double longitude;
    private String cep;
    private Integer numero;
    private String complemento;

}
