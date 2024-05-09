package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.*;

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
