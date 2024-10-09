package school.sptech.saferide.model.entity.endereco;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.usuario.Usuario;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String nome;
    private String cep;
    private Integer numero;
    private String complemento;
    @ManyToOne
    private Usuario usuario;
}
