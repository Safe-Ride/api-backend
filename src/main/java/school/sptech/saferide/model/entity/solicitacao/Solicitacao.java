package school.sptech.saferide.model.entity.solicitacao;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.StatusSolicitacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Usuario responsavel;
    @ManyToOne
    private Usuario motorista;
    @ManyToOne
    private Endereco endereco;
    @ManyToOne
    private Escola escola;
    @ManyToOne
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

    // Contrutor para a solicitação inicial
    public Solicitacao(Usuario responsavel, Usuario motorista, Endereco endereco, Escola escola, Dependente dependente, HorarioTrajeto periodo, String tipo, String diaSemana) {
        this.responsavel = responsavel;
        this.motorista = motorista;
        this.endereco = endereco;
        this.escola = escola;
        this.dependente = dependente;
        this.periodo = periodo;
        this.tipo = tipo;
        this.diaSemana = diaSemana;
        this.status = StatusSolicitacao.PENDENTE_MOTORISTA;
    }
}
