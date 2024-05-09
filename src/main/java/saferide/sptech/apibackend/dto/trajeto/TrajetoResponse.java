package saferide.sptech.apibackend.dto.trajeto;

import lombok.Data;
import saferide.sptech.apibackend.entity.DiaSemana;
import saferide.sptech.apibackend.entity.TipoTrajeto;

@Data
public class TrajetoResponse {

    private Integer id;
    private TipoTrajeto tipo;
    private DiaSemana diaSemana;
    private TrajetoEscolaResponse escola;
    private TrajetoMotoristaResponse motorista;

}
