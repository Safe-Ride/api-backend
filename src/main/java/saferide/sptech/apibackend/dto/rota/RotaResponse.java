package saferide.sptech.apibackend.dto.rota;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.dto.trajeto.TrajetoResponse;

@Data
public class RotaResponse {

    private DependenteResponse dependente;
    private TrajetoResponse trajeto;
    private Endereco endereco;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Endereco {

        private Integer id;
        private Double latitude;
        private Double longitude;
        private String cep;
        private Integer numero;
        private String logradouro;
        private String complemento;
        private String bairro;
        private String localidade;
        private String uf;

    }

}
