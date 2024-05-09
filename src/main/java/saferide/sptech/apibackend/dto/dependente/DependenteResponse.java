package saferide.sptech.apibackend.dto.dependente;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DependenteResponse {

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String serie;
    private DependenteResponsavelResponse responsavel;
    private DependenteEscolaResponse escola;
    private DependenteMotoristaResponse motorista;

}
