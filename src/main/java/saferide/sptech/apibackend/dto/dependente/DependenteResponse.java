package saferide.sptech.apibackend.dto.dependente;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DependenteResponse {

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String serie;
    private Usuario responsavel;
    private Escola escola;
    private Usuario motorista;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Usuario {

        private Integer id;
        private String nome;

    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Escola {

        private Integer id;
        private String nome;

    }

}
