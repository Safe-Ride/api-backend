package saferide.sptech.apibackend.dto.dependente;

import lombok.Getter;
import lombok.Setter;
import saferide.sptech.apibackend.entity.Usuario;

import java.time.LocalDate;

@Getter
@Setter
public class DependenteResponse {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String escola;
    private String serie;
    private Usuario usuario;
}
