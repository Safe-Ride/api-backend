package saferide.sptech.apibackend.dto.usuario;

import lombok.Data;

@Data
public class UsuarioUpdate {
    private Integer id;
    private String campo;
    private String alteracao;
}
