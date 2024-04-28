package saferide.sptech.apibackend.dto.endereco;

import lombok.Data;
import saferide.sptech.apibackend.entity.TipoCliente;
import saferide.sptech.apibackend.entity.Usuario;

import java.time.LocalDate;

@Data
public class EnderecoUsuarioResponse {

    private int id;
    private String nome;

}
