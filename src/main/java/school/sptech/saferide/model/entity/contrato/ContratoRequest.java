package school.sptech.saferide.model.entity.contrato;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ContratoRequest {
    private Integer motoristaId;
    private Integer responsavelId;
    private List<Integer> dependentesId;
    private Double valor;
}
