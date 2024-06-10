package saferide.sptech.apibackend.dto.rota;

import lombok.Data;
import saferide.sptech.apibackend.entity.Status;

@Data
public class RotaUpdateRequest {

    private Integer rotaId;
    private Status status;

}
