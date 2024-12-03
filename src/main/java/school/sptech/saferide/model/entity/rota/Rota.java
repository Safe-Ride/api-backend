package school.sptech.saferide.model.entity.rota;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import school.sptech.saferide.model.enums.StatusDependente;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Trajeto trajeto;
    @ManyToOne
    private Dependente dependente;
    @ManyToOne
    private Endereco endereco;
    private StatusDependente status;
    private LocalDateTime horario;
}
