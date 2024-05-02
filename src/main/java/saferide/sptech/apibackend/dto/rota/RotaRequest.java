package saferide.sptech.apibackend.dto.rota;

import lombok.Data;

@Data
public class RotaRequest {

    private int trajetoId;
    private int dependenteId;
    private int enderecoId;

}
