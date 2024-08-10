package school.sptech.saferide.model.entity.transporte;

import lombok.Data;

@Data
public class TransporteRequest {
    private String placa;
    private String cnpj;
    private String cnh;
    private String crm;
    private String crmc;
    private Integer usuarioId;
}
