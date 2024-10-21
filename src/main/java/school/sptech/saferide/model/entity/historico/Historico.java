package school.sptech.saferide.model.entity.historico;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Trajeto trajeto;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
}
