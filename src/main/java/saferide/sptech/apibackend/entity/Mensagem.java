package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime data;
    private Status status;
    @ManyToOne
    private Historico historico;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Dependente dependente;

}
