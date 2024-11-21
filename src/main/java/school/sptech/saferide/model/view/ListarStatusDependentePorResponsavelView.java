package school.sptech.saferide.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.yaml.snakeyaml.events.Event;
import school.sptech.saferide.model.enums.StatusDependente;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@IdClass(ListarStatusDependentePorResponsavelKey.class)
@Table(name = "v_listar_status_dependente_por_responsavel")
public class ListarStatusDependentePorResponsavelView {

    @Id
    @Column(name = "historico_id")
    private Integer historicoId;
    @Column(name = "trajeto_id")
    private Integer trajetoId;
    @Id
    @JsonFormat(pattern = "HH:mm")
    @Column(name = "horario_inicio")
    private LocalDateTime horarioInicio;
    @Id
    @JsonFormat(pattern = "HH:mm")
    @Column(name = "horario_fim")
    private LocalDateTime horarioFim;
    @Column(name = "trajeto_sentido")
    private String trajetoSentido;
    @Column(name = "trajeto_horario")
    private String trajetoHorario;
    @Column(name = "de_es_nome")
    private String escolaNome;
    @Id
    @Column(name = "de_id")
    private Integer dependenteId;
    @Column(name = "de_nome")
    private String dependenteNome;
    @Column(name = "de_en_nome")
    private String dependenteEndereco;
    @Column(name = "re_id")
    private Integer responsavelId;
    @Column(name = "re_nome")
    private String responsavelNome;
    @Id
    @Column(name = "mensagem_status")
    @Enumerated(EnumType.ORDINAL)
    private StatusDependente mensagemStatus;

    public String getMensagemStatus() {
        return mensagemStatus.exibicao;
    }
}
