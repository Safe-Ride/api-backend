package saferide.sptech.apibackend.dto.trajeto;

import lombok.Data;
import saferide.sptech.apibackend.entity.Status;

@Data
public class TrajetoStatusUpdate {

    private Integer trajetoId;
    private Integer dependenteId;
    private Integer enderecoId;
    private Status status;

}
