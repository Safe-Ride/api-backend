package school.sptech.saferide.model.entity.conversa;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.mensagem.Mensagem;
import school.sptech.saferide.model.entity.usuario.Usuario;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Usuario responsavel;
    @ManyToOne
    private Usuario motorista;
    @OneToMany(mappedBy = "conversa")
    private List<Mensagem> mensagens;
}
