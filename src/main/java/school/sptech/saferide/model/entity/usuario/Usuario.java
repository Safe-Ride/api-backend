package school.sptech.saferide.model.entity.usuario;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.imagem.Imagem;
import school.sptech.saferide.model.enums.TipoUsuario;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoUsuario tipo;
    @OneToMany(mappedBy = "responsavel")
    private List<Dependente> dependentes;
    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;
    @OneToMany(mappedBy = "motorista")
    private List<Dependente> clientes;
    @ManyToOne
    private Imagem imagem;
}
