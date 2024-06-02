package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
