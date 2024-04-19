package saferide.sptech.apibackend.dto.dependente;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DependenteRequestUpdate {
    private String nome;
    private LocalDate dataNascimento;
    private String escola;
    private String serie;
}
