package saferide.sptech.apibackend.dto.dependente;

import lombok.Data;
import saferide.sptech.apibackend.entity.TipoCliente;

import java.time.LocalDate;

@Data
public class DependenteResponsavelResponse {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

}
