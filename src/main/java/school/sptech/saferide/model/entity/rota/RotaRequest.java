package school.sptech.saferide.model.entity.rota;

import lombok.Data;

@Data
public class RotaRequest {
    private Integer trajetoId;
    private Integer dependenteId;
    private Integer enderecoId;
}
