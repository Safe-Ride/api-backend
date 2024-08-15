package school.sptech.saferide.model.entity.pagamento;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.contrato.Contrato;
import school.sptech.saferide.model.enums.StatusPagamento;
import school.sptech.saferide.model.enums.TipoPagamento;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private LocalDate dataEfetuacao;
    private Double valor;
    private TipoPagamento tipo;
    private StatusPagamento status;
    @ManyToOne
    private Contrato contrato;
}
