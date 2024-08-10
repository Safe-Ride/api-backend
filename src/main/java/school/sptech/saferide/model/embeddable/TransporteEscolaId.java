package school.sptech.saferide.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TransporteEscolaId implements Serializable {
    @Column(name = "transporte_id")
    private int transporteId;
    @Column(name = "escola_id")
    private int escolaId;
}
