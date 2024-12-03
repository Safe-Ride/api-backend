package school.sptech.saferide.model.entity.rota;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class RotaEscolaEndereco {
    private Double latitude;
    private Double longitude;



    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
