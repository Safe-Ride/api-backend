package school.sptech.saferide.model.entity.transporte;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.usuario.Usuario;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String placa;
    private String cnpj;
    private String cnh;
    private String crm;
    private String crmc;
    @ManyToOne
    private Usuario usuario;
}
