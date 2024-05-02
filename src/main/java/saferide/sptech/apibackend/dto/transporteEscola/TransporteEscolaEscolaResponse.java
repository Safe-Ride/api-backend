package saferide.sptech.apibackend.dto.transporteEscola;

import lombok.Data;
import saferide.sptech.apibackend.dto.escola.EscolaEnderecoResponse;

@Data
public class TransporteEscolaEscolaResponse {

    private Integer id;
    private String nome;
    private EscolaEnderecoResponse endereco;

}
