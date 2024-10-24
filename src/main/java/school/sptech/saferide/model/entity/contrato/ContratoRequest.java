package school.sptech.saferide.model.entity.contrato;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoRequest {
    private Integer motoristaId;
    private Integer responsavelId;
    private List<Integer> dependentesId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valor;
}
