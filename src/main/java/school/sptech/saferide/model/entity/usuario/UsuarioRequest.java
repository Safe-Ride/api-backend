package school.sptech.saferide.model.entity.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import school.sptech.saferide.constants.SafeRideConstants;
import school.sptech.saferide.model.enums.TipoUsuario;

import java.time.LocalDate;

@Getter
public class UsuarioRequest {
    @NotBlank(message = SafeRideConstants.USUARIO_INVALID_NAME)
    @Size(min = 3, message = SafeRideConstants.USUARIO_INVALID_NAME)
    private String nome;
    @NotBlank(message = SafeRideConstants.USUARIO_INVALID_EMAIL)
    @Email (message = SafeRideConstants.USUARIO_INVALID_EMAIL)
    private String email;
    @NotBlank(message = SafeRideConstants.USUARIO_INVALID_PASSWORD)
    @Size(min = 4, message = SafeRideConstants.USUARIO_INVALID_PASSWORD)
    private String senha;
    private String cpf;
    private String telefone;
    @NotNull(message = SafeRideConstants.USUARIO_INVALID_DATA_NASCIMENTO)
    private LocalDate dataNascimento;
    @NotNull(message = SafeRideConstants.USUARIO_INVALID_TIPO)
    private TipoUsuario tipo;
}
