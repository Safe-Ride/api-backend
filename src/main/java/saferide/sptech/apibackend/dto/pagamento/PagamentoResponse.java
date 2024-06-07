package saferide.sptech.apibackend.dto.pagamento;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import saferide.sptech.apibackend.entity.SituacaoPagamento;
import saferide.sptech.apibackend.entity.TipoPagamento;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagamentoResponse {

    private Integer id;
    private CobradorDto cobrador;
    private PagadorDto pagador;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private LocalDate dataEfetuacao;
    private Double valor;
    private TipoPagamento tipo;
    private SituacaoPagamento status;

    @Data
    @Builder
    public static class CobradorDto {

        private Integer id;
        private String nome;

    }

    @Data
    @Builder
    public static class PagadorDto {

        private Integer id;
        private String nome;

    }

}
