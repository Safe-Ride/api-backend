package saferide.sptech.apibackend.dto.transporte;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import saferide.sptech.apibackend.entity.TipoCliente;
import saferide.sptech.apibackend.entity.Usuario;

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
