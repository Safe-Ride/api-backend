package saferide.sptech.apibackend.dto.dependente;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DependenteMotoristaResponse {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

}
