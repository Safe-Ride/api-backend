package school.sptech.saferide.model.entity.escola;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.endereco.Endereco;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @ManyToOne
    private Endereco endereco;
}
