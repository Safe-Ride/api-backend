package saferide.sptech.apibackend.dto.cliente;

import lombok.Getter;
import saferide.sptech.apibackend.entity.TipoCliente;

import java.time.LocalDate;

@Getter
public class ClienteRequest {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoCliente tipo;
}
