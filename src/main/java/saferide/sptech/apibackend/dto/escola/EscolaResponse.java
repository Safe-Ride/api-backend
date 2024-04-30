package saferide.sptech.apibackend.dto.escola;

import lombok.Data;
import saferide.sptech.apibackend.entity.Endereco;

import java.util.List;

@Data
public class EscolaResponse {

    private Integer id;
    private String nome;
    private EscolaEnderecoResponse endereco;
    private List<EscolaTransporteResponse> trasportes;

}
