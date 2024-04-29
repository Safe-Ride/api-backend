package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoCliente tipo;
    @OneToMany(mappedBy = "usuario")
    private List<Dependente> dependentes;
    @OneToMany(mappedBy = "usuario")
    private List<Endereco> enderecos;

}
