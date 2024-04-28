package saferide.sptech.apibackend.dto.usuario;

import lombok.Getter;
import saferide.sptech.apibackend.entity.TipoCliente;

import java.time.LocalDate;

@Getter
public class UsuarioRequest {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoCliente tipo;

}
