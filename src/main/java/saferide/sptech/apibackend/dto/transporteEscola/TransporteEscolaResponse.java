package saferide.sptech.apibackend.dto.transporteEscola;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
public class TransporteEscolaResponse {

    private Transporte transporte;
    private Escola escola;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Transporte {

        private Integer id;
        private Integer morotistaId;

    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Escola {

        private Integer id;
        private String nome;

    }

}
