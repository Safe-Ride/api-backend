package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
//    @OneToMany(mappedBy = "escolas")
//    private List<Transporte> transportes;
    @ManyToOne
    private Endereco endereco;
}
