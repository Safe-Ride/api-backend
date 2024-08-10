package school.sptech.saferide.model.entity.contrato;

import lombok.Getter;

@Getter
public class ContratoRequest {
    private Integer motoristaId;
    private Integer responsavelId;
    private Integer dependenteId;
    private Double valor;
}
