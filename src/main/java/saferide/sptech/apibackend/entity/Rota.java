package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Status status;

}
