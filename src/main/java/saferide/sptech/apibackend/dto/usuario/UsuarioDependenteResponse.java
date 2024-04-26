package saferide.sptech.apibackend.dto.usuario;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDependenteResponse {

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String escola;
    private String serie;

}
