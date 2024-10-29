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
    @Column(name = "historico_id")
    private Integer id;
    private Integer trajetoId;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
    private String trajetoSentido;
    private String trajetoHorario;
    private String escolaCep;
    private Integer dependenteId;
    private String dependenteNome;
    private String dependenteCep;
    private Integer responsavelId;
    private LocalDateTime horarioNaVan;
    private String resposanvelNome;







}
