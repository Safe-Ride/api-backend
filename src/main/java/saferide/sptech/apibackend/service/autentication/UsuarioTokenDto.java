package saferide.sptech.apibackend.service.autentication;

import lombok.Data;
import saferide.sptech.apibackend.entity.TipoUsuario;

import java.time.LocalDate;

@Data
public class UsuarioTokenDto {

    private String token;
    private Integer usuarioId;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoUsuario tipo;
    private String foto;

}
