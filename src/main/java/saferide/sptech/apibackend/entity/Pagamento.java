package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Usuario cobrador;
    @ManyToOne
    private Usuario pagador;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private LocalDate dataEfetuacao;
    private Double valor;
    private TipoPagamento tipo;
    private SituacaoPagamento situacao;

}
