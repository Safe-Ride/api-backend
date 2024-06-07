package saferide.sptech.apibackend.dto.pagamento;

import lombok.Getter;
import saferide.sptech.apibackend.entity.TipoPagamento;

import java.time.LocalDate;

@Getter
public class PagamentoRequest {

    private Integer cobradorId;
    private Integer pagadorId;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private LocalDate dataEfetuacao;
    private Double valor;
    private TipoPagamento tipo;

}
