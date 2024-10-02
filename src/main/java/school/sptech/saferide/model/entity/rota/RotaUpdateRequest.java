package school.sptech.saferide.model.entity.rota;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.saferide.model.enums.StatusDependente;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RotaUpdateRequest {
    private StatusDependente status;
}
