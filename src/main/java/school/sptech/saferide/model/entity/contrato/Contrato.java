package school.sptech.saferide.model.entity.contrato;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.pagamento.Pagamento;
import school.sptech.saferide.model.entity.usuario.Usuario;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Usuario motorista;
    @ManyToOne
    private Usuario responsavel;
    @OneToMany(mappedBy = "contrato")
    private List<Dependente> dependentes;
    private Double valor;
    private LocalDate data;
    @OneToMany(mappedBy = "contrato")
    private List<Pagamento> pagamentos;
}
