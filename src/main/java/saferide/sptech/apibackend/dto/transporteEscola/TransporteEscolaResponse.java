package saferide.sptech.apibackend.dto.transporteEscola;

import lombok.Data;

@Data
public class TransporteEscolaResponse {

    private TransporteEscolaTransporteResponse transporte;
    private TransporteEscolaEscolaResponse escola;

}
