package school.sptech.saferide.model.entity.pagamento;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import school.sptech.saferide.model.enums.StatusPagamento;
import school.sptech.saferide.model.enums.TipoPagamento;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagamentoResponse {
    private Integer id;
    private Contrato contrato;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private LocalDate dataEfetuacao;
    private Double valor;
    private TipoPagamento tipo;
    private StatusPagamento status;

    @Data
    @Builder
    public static class Contrato {
        private Integer id;
        private Usuario motorista;
        private Usuario responsavel;
        private Dependente dependente;

        @Data
        @Builder
        public static class Usuario {
            private Integer id;
            private String nome;
        }

        @Data
        @Builder
        public static class Dependente {
            private Integer id;
            private String nome;
        }
    }
}
