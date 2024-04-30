package saferide.sptech.apibackend.dto.usuario;

import lombok.Data;
import saferide.sptech.apibackend.entity.TipoCliente;

import java.time.LocalDate;
import java.util.List;

@Data
public class    UsuarioResponse {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoCliente tipo;
    private List<UsuarioDependenteResponse> dependentes;
    private List<UsuarioEnderecoResponse> enderecos;

}
