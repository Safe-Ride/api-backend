package school.sptech.saferide.model.entity.rota;

import lombok.Data;

@Data
public class RotaRequest {
    private int trajetoId;
    private int dependenteId;
    private int enderecoId;
}
