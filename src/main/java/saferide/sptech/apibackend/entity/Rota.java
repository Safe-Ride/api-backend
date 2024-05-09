package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.*;
import saferide.sptech.apibackend.entity.id.RotaId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rota {

    @EmbeddedId
    private RotaId id;

    @ManyToOne
    @MapsId("trajetoId")
    private Trajeto trajeto;
    @ManyToOne
    @MapsId("dependenteId")
    private Dependente dependente;
    @ManyToOne
    @MapsId("enderecoId")
    private Endereco endereco;

}
