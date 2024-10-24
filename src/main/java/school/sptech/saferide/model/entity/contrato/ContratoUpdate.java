package school.sptech.saferide.model.entity.contrato;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ContratoUpdate {
    private Integer id;
    private Integer dependenteId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valor;
}
