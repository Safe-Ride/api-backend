package school.sptech.saferide.model.entity.conversa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversaRequest {
    private Integer responsavelId;
    private Integer motoristaId;
}
