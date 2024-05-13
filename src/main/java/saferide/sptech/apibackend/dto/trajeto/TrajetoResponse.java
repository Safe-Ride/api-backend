package saferide.sptech.apibackend.dto.trajeto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import saferide.sptech.apibackend.entity.DiaSemana;
import saferide.sptech.apibackend.entity.TipoTrajeto;

@Data
public class TrajetoResponse {

    private Integer id;
    private TipoTrajeto tipo;
    private DiaSemana diaSemana;
    private Escola escola;
    private TrajetoMotoristaResponse motorista;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Escola {

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

}
