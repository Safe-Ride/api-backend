package saferide.sptech.apibackend.dto.cliente;

import lombok.Getter;
import lombok.Setter;
import saferide.sptech.apibackend.entity.TipoCliente;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteResponse {
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoCliente tipo;
}
