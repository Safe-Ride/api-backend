package school.sptech.saferide.model.entity.trajeto;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.DiaSemana;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.TipoTrajeto;

import java.util.List;

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
    private HorarioTrajeto horario;
    private DiaSemana diaSemana;
    private Boolean ativo;
    @ManyToOne
    private Escola escola;
    @ManyToOne
    private Usuario motorista;
    @OneToMany(mappedBy = "trajeto")
    private List<Rota> rotas;
}
