package saferide.sptech.apibackend.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import saferide.sptech.apibackend.constants.MessagesConstants;
import saferide.sptech.apibackend.entity.TipoUsuario;

import java.time.LocalDate;

@Getter
public class UsuarioRequest {

    @NotBlank(message = MessagesConstants.USUARIO_INVALID_NOME_EMPTY)
    @Size(message = MessagesConstants.USUARIO_INVALID_NOME_SIZE)
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    @NotNull
    private TipoUsuario tipo;

}
