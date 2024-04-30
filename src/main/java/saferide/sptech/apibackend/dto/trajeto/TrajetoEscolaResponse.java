package saferide.sptech.apibackend.dto.trajeto;

import lombok.Data;
import saferide.sptech.apibackend.dto.escola.EscolaEnderecoResponse;
import saferide.sptech.apibackend.entity.DiaSemana;
import saferide.sptech.apibackend.entity.TipoTrajeto;

@Data
public class TrajetoEscolaResponse {

    private Integer id;
    private String nome;
    private EscolaEnderecoResponse endereco;

}
