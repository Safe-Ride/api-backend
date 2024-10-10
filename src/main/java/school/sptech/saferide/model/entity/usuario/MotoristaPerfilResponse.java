package school.sptech.saferide.model.entity.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.saferide.model.entity.imagem.Imagem;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MotoristaPerfilResponse {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private Imagem imagem;
    private Integer transporteId;
    private String placa;
    private String cnpj;

}
