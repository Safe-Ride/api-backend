package school.sptech.saferide.model.entity.dependente;

import jakarta.persistence.*;
import lombok.*;
import school.sptech.saferide.model.entity.contrato.Contrato;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.usuario.Usuario;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DependentePerfilResponse {

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String serie;
    private String nomeEscola;
    private String nomeMotorista;
    private String telefoneMotorista;
    private String placaTransporte;
    private String foto;

}

