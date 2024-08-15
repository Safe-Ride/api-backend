package school.sptech.saferide.model.entity.mensagem;

import lombok.Data;
import school.sptech.saferide.model.enums.StatusDependente;

@Data
public class MensagemRequest {
    private Integer conversaId;
    private Integer usuarioId;
    private Integer dependenteId;
    private StatusDependente status;
}
