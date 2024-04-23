package saferide.sptech.apibackend.dto.endereco;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EnderecoRequest {
    private Double latitude;
    private Double longitude;
    private String cep;
    private Integer numero;
    private String complemento;
}
