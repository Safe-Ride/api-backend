package saferide.sptech.apibackend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;
import saferide.sptech.apibackend.entity.id.TransporteEscolaId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
