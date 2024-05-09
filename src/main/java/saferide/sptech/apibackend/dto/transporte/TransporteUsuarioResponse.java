package saferide.sptech.apibackend.dto.transporte;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransporteUsuarioResponse {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

}
