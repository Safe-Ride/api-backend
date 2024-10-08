package school.sptech.saferide.model.entity.contrato;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import school.sptech.saferide.model.enums.StatusPagamento;
import school.sptech.saferide.model.enums.TipoPagamento;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContratoResponse {
    private Integer id;
    private Usuario motorista;
    private Usuario responsavel;
    private List<Dependente> dependentes;
    private Double valor;
    private LocalDate data;
    private List<Pagamento> pagamentos;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Usuario {
        private Integer id;
        private String nome;
        private String foto;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Dependente {
        private Integer id;
        private String nome;
        private String foto;
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Pagamento {
        private LocalDate dataVencimento;
        private LocalDate dataEfetuacao;
        private Double valor;
        private TipoPagamento tipo;
        private StatusPagamento status;
    }
}
