package school.sptech.saferide.model.entity.trajeto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.saferide.model.enums.DiaSemana;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.TipoTrajeto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrajetoRequest {
    private TipoTrajeto tipo;
    private HorarioTrajeto horario;
    private DiaSemana diaSemana;
    private Integer escolaId;
    private Integer motoristaId;
}
