package saferide.sptech.apibackend.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import saferide.sptech.apibackend.constants.SafeRideConstants;
import saferide.sptech.apibackend.entity.TipoUsuario;

import java.time.LocalDate;

@Getter
public class UsuarioRequest {

    @NotBlank(message = SafeRideConstants.USUARIO_INVALID_NAME)
    @Size(min = 3, message = SafeRideConstants.USUARIO_INVALID_NAME)
    private String nome;
    @NotBlank(message = SafeRideConstants.USUARIO_INVALID_EMAIL)
    @Pattern(regexp = SafeRideConstants.REGEX_EMAIL, message = SafeRideConstants.USUARIO_INVALID_EMAIL)
    private String email;
    @NotBlank(message = SafeRideConstants.USUARIO_INVALID_PASSWORD)
    @Size(min = 4, message = SafeRideConstants.USUARIO_INVALID_PASSWORD)
    private String senha;
    private String cpf;
    private String telefone;
    @NotNull(message = SafeRideConstants.USUARIO_INVALID_DATA_NASCIMENTO)
//    @Pattern(regexp = SafeRideConstants.REGEX_DATA_NASCIMENTO, message = SafeRideConstants.USUARIO_INVALID_DATA_NASCIMENTO)
    private LocalDate dataNascimento;
    @NotNull(message = SafeRideConstants.USUARIO_INVALID_TIPO)
    private TipoUsuario tipo;

}
