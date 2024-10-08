package school.sptech.saferide.model.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RotaId implements Serializable {
    @Column(name = "trajeto_id")
    private int trajetoId;
    @Column(name = "dependente_id")
    private int dependenteId;
    @Column(name = "endereco_id")
    private int enderecoId;
}
