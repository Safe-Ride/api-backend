package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Usuario responsavel;
    @ManyToOne
    private Usuario motorista;
    @OneToMany(mappedBy = "chat")
    private List<Mensagem> mensagems;

}
