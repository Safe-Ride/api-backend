package saferide.sptech.apibackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import saferide.sptech.apibackend.entity.id.RotaId;

@Entity
@Data
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
