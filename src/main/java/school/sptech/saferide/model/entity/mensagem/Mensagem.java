package school.sptech.saferide.model.entity.mensagem;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.StatusDependente;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;
    private StatusDependente status;
    private Boolean lida;
    @ManyToOne
    private Conversa conversa;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Dependente dependente;

    public Mensagem(StatusDependente status) {
        this.status = status;
    }
}
