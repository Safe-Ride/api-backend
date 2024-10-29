package school.sptech.saferide.model.entity.solicitacao;

import lombok.Data;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.StatusSolicitacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SolicitacaoRequest {
    private Integer id;
    private BigDecimal valor;
    private LocalTime horarioIda;
    private LocalTime horarioVolta;
    private LocalDate contratoInicio;
    private LocalDate contratoFim;
    private StatusSolicitacao status;
}
