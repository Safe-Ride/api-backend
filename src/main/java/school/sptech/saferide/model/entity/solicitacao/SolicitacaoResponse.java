package school.sptech.saferide.model.entity.solicitacao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import school.sptech.saferide.model.entity.imagem.Imagem;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.StatusSolicitacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SolicitacaoResponse {
    private Integer id;
    private Motorista motorista;
    private Responsavel responsavel;
    private Endereco endereco;
    private Escola escola;
    private Dependente dependente;
    private HorarioTrajeto periodo;
    private BigDecimal valor;
    private LocalTime horarioIda;
    private LocalTime horarioVolta;
    private LocalDate contratoInicio;
    private LocalDate contratoFim;
    private String tipo;
    private String diaSemana;
    private StatusSolicitacao status;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Motorista {
        private Integer id;
        private String nome;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Responsavel {
        private Integer id;
        private String nome;
        private Imagem imagem;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Escola {
        private Integer id;
        private String nome;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Endereco {
        private Integer id;
        private String nome;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Dependente {
        private Integer id;
        private String nome;
    }
}
