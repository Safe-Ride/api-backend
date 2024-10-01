package school.sptech.saferide.model.entity.conversa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import school.sptech.saferide.model.entity.imagem.Imagem;
import school.sptech.saferide.model.entity.mensagem.Mensagem;
import school.sptech.saferide.model.enums.StatusDependente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListarConversasMotorista {
    private Integer id;
    private String foto;
    private String nome;
    private StatusDependente mensagem;
    private LocalDateTime horario;
    private Long qtdMensagens;

    public String getHorario() {
        return horario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
    }

    public String getMensagem() {
        return mensagem.exibicao;
    }
}
