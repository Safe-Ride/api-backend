package saferide.sptech.apibackend.dto.cliente;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ClienteRequestUpdateDto {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
}
