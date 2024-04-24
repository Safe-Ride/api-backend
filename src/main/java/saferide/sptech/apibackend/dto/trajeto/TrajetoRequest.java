package saferide.sptech.apibackend.dto.trajeto;

import lombok.Data;
import saferide.sptech.apibackend.entity.DiaSemana;
import saferide.sptech.apibackend.entity.TipoTrajeto;

@Data
public class TrajetoRequest {
    private String escola;
    private TipoTrajeto tipo;
    private DiaSemana diaSemana;
}
