package saferide.sptech.apibackend.dto.usuario;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import saferide.sptech.apibackend.constants.SafeRideConstants;

import java.time.LocalDate;

@Getter
public class UsuarioRequestUpdate {

    @Size(min = 3, message = SafeRideConstants.USUARIO_INVALID_NOME)
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

}
