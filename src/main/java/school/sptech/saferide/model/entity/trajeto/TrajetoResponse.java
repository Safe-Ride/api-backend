package school.sptech.saferide.model.entity.trajeto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import school.sptech.saferide.model.enums.DiaSemana;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.StatusDependente;
import school.sptech.saferide.model.enums.TipoTrajeto;

import java.time.LocalDate;
import java.util.List;

@Data
public class TrajetoResponse {
    private Integer id;
    private TipoTrajeto tipo;
    private HorarioTrajeto horario;
    private DiaSemana diaSemana;
    private Escola escola;
    private Motorista motorista;
    private List<DependenteComEndereco> dependentes;

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
            private Integer numero;
        }
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Motorista {
        private Integer id;
        private String nome;
    }

    @Data
    @Builder
    public static class DependenteComEndereco {
        private Integer id;
        private String nome;
        private StatusDependente status;
        private LocalDate dataNascimento;
        private String serie;
        private Endereco endereco;

        @Data
        @Builder
        public static class Endereco {
            private Integer id;
            private String cep;
            private Integer numero;
            private String complemento;
        }
    }
}