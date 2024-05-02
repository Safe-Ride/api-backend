package saferide.sptech.apibackend.dto.transporte;

import lombok.Data;

@Data
public class TransporteRequestUpdate {

    private String placa;
    private String cnpj;
    private String cnh;
    private String crm;
    private String crmc;

}
