package saferide.sptech.apibackend.service.autentication;

import lombok.Data;
import saferide.sptech.apibackend.entity.TipoUsuario;

@Data
public class UsuarioTokenDto {

    private  Integer userId;
    private String nome;
    private String email;
    private String token;
    private TipoUsuario tipo;

}
