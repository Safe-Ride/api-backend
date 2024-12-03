package school.sptech.saferide.model.entity.rota;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RotaRequest {
    private Integer trajetoId;
    private Integer dependenteId;
    private Integer enderecoId;
    private LocalDateTime horario;
}
