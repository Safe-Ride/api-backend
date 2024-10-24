package school.sptech.saferide.model.entity.contrato;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoUpdate {
    private Integer id;
    private List<Integer> dependentesId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valor;
}
