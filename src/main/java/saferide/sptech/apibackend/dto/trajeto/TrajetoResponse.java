package saferide.sptech.apibackend.dto.trajeto;

import lombok.Getter;
import lombok.Setter;
import saferide.sptech.apibackend.entity.DiaSemana;
import saferide.sptech.apibackend.entity.TipoTrajeto;

@Getter
@Setter
public class TrajetoResponse {
    private Integer id;
    private String escola;
    private TipoTrajeto tipo;
    private DiaSemana diaSemana;
}
