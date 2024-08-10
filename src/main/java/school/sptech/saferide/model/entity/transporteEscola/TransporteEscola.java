package school.sptech.saferide.model.entity.transporteEscola;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;
import school.sptech.saferide.model.embeddable.TransporteEscolaId;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.transporte.Transporte;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransporteEscola {
    @EmbeddedId
    private TransporteEscolaId id;
    @ManyToOne
    @MapsId("transporteId")
    private Transporte transporte;
    @ManyToOne
    @MapsId("escolaId")
    private Escola escola;
}
