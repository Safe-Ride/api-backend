package school.sptech.saferide.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "v_listar_status_dependente_por_responsavel")
public class ListarStatusDependentePorResponsavelView {
    @Id
    private Integer responsavelId;
    private String nomeDependente;
    private String endereco;
    private String encerecoCep;
    private String status;
    private Date horario;
    private String enderocoEscola;






}
