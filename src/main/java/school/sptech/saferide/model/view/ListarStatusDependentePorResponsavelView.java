package school.sptech.saferide.model.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.yaml.snakeyaml.events.Event;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@Table(name = "v_listar_status_dependente_por_responsavel")
public class ListarStatusDependentePorResponsavelView {
    @Id
    private Integer responsavelId;
    @Column(name = "dependente_nome")
    private String dependenteNome;
    private String enderecoCep;
    private LocalDateTime horaStatus;
    private String sentidoTrajeto;
    private  horario;
    private String escolaCep;






}
