package school.sptech.saferide.model.entity.rota;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import school.sptech.saferide.model.entity.dependente.DependenteResponse;
import school.sptech.saferide.model.entity.trajeto.TrajetoResponse;
import school.sptech.saferide.model.enums.StatusDependente;

@Data
public class RotaResponse {
    private Integer id;
    private DependenteResponse dependente;
    private TrajetoResponse trajeto;
    private Endereco endereco;
    private StatusDependente status;

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
