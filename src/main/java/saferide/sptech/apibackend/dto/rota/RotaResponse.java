package saferide.sptech.apibackend.dto.rota;

import lombok.Data;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.dto.trajeto.TrajetoResponse;

@Data
public class RotaResponse {

    private DependenteResponse dependente;
    private TrajetoResponse trajeto;
    private RotaEnderecoDto endereco;

}
