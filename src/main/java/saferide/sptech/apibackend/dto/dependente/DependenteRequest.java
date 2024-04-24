package saferide.sptech.apibackend.dto.dependente;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DependenteRequest {
    private String nome;
    private LocalDate dataNascimento;
    private String escola;
    private String serie;
    private Integer usuarioId;
}
