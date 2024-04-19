package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String escola;
    private String serie;
    @ManyToOne
    private Cliente cliente;
}
