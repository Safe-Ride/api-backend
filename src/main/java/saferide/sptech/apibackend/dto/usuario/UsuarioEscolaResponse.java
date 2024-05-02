package saferide.sptech.apibackend.dto.usuario;

import lombok.Data;
import saferide.sptech.apibackend.dto.escola.EscolaEnderecoResponse;
import saferide.sptech.apibackend.dto.escola.EscolaTransporteResponse;

import java.util.List;

@Data
public class UsuarioEscolaResponse {

    private Integer id;
    private String nome;
    private EscolaEnderecoResponse endereco;
    private List<EscolaTransporteResponse> escolas;

}
