package saferide.sptech.apibackend.dto.escola;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EscolaResponse {

    private Integer id;
    private String nome;
    private Endereco endereco;
    private List<Transporte> trasportes;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Endereco {

        private Integer id;
        private String cep;

    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Transporte {

        private Integer id;
        private String nome;

    }

}
