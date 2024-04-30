package saferide.sptech.apibackend.dto.rota;

import lombok.Data;

@Data
public class RotaEnderecoDto {
    private Integer id;
    private Double latitude;
    private Double longitude;
    private Integer numero;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
