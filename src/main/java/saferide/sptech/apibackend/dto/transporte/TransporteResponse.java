package saferide.sptech.apibackend.dto.transporte;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class TransporteResponse {

    private Integer id;
    private String placa;
    private String cnpj;
    private String cnh;
    private String crm;
    private String crmc;
    @ManyToOne
    private TransporteUsuarioResponse usuario;

}
