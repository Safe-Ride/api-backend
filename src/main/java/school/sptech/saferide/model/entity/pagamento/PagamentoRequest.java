package school.sptech.saferide.model.entity.pagamento;

import lombok.Getter;
import school.sptech.saferide.model.enums.TipoPagamento;

import java.time.LocalDate;

@Getter
public class PagamentoRequest {
    private Integer contratoId;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private LocalDate dataEfetuacao;
    private Double valor;
    private TipoPagamento tipo;
}
