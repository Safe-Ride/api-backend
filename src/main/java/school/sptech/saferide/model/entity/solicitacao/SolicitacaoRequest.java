package school.sptech.saferide.model.entity.solicitacao;

import lombok.Data;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.StatusSolicitacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SolicitacaoRequest {
    private Integer responsavelId;
    private Integer motoristaId;
    private Integer enderecoId;
    private Integer escolaId;
    private Integer dependenteId;
    private HorarioTrajeto periodo;
    private BigDecimal valor;
    private LocalTime horarioIda;
    private LocalTime horarioVolta;
    private LocalDate contratoInicio;
    private LocalDate contratoFim;
    private String tipo;
    private String diaSemana;
    private StatusSolicitacao status;
}
