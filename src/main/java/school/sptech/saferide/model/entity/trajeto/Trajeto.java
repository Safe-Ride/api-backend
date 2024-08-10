package school.sptech.saferide.model.entity.trajeto;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.DiaSemana;
import school.sptech.saferide.model.enums.TipoTrajeto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trajeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private TipoTrajeto tipo;
    private DiaSemana diaSemana;
    @ManyToOne
    private Escola escola;
    @ManyToOne
    private Usuario motorista;
}
