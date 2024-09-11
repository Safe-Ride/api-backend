package school.sptech.saferide.model.entity.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.sptech.saferide.model.enums.StatusDependente;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsavelListarMotoristas {
    private int id;
    private String nome;
    private String foto;
    private StatusDependente mensagem;
    private LocalDate horairo;
    private Integer qtdMensagens;
}
