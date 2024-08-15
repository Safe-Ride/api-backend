package school.sptech.saferide.model.entity.rota;

import lombok.Data;
import school.sptech.saferide.model.enums.StatusDependente;

@Data
public class RotaUpdateRequest {
    private Integer rotaId;
    private StatusDependente status;
}
