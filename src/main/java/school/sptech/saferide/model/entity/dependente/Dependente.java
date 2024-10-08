package school.sptech.saferide.model.entity.dependente;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.contrato.Contrato;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.imagem.Imagem;
import school.sptech.saferide.model.entity.usuario.Usuario;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String serie;
    @ManyToOne
    private Usuario responsavel;
    @ManyToOne
    private Escola escola;
    @ManyToOne
    private Usuario motorista;
    @ManyToOne
    private Contrato contrato;
    @ManyToOne
    private Imagem imagem;
}
