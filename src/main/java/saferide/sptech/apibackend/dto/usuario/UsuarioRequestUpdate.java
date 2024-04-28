package saferide.sptech.apibackend.dto.usuario;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UsuarioRequestUpdate {

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

}
