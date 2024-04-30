package saferide.sptech.apibackend.dto.dependente;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DependenteResponse {

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String serie;
    private DependenteUsuarioResponse usuario;
    private DependenteEscolaResponse escola;

}
