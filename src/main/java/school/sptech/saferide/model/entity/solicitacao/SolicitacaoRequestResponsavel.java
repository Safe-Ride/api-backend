package school.sptech.saferide.model.entity.solicitacao;

import lombok.Data;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.StatusSolicitacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SolicitacaoRequestResponsavel {
    private Integer responsavelId;
    private Integer motoristaId;
    private Integer dependenteId;
    private HorarioTrajeto periodo;
    private String tipo;
    private String diaSemana;
    private Integer enderecoId;
    private StatusSolicitacao status;
}
