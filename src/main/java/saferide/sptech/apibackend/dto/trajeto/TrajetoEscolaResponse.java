package saferide.sptech.apibackend.dto.trajeto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrajetoEscolaResponse {

    private Integer id;
    private String nome;
    private Endereco endereco;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Endereco {

        private Integer id;
        private String cep;

    }

}
