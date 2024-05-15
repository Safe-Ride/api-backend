package saferide.sptech.apibackend.dto.historico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoRequest {

    private Integer responsavelId;
    private Integer motoristaId;

}
