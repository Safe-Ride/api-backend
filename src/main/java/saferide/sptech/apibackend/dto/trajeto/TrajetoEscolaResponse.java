package saferide.sptech.apibackend.dto.trajeto;

import lombok.Data;
import saferide.sptech.apibackend.dto.escola.EscolaEnderecoResponse;

@Data
public class TrajetoEscolaResponse {

    private Integer id;
    private String nome;
    private EscolaEnderecoResponse endereco;

}
