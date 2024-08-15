package school.sptech.saferide.model.entity.dependente;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DependenteRequest {
    private String nome;
    private LocalDate dataNascimento;
    private String serie;
    private Integer responsavelId;
    private Integer escolaId;
}
