package school.sptech.saferide.model.view;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import school.sptech.saferide.model.enums.StatusDependente;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class ListarStatusDependentePorResponsavelKey implements Serializable {
    private Integer historicoId;
    private Integer dependenteId;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
    private StatusDependente mensagemStatus;

    // Override equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListarStatusDependentePorResponsavelKey that = (ListarStatusDependentePorResponsavelKey) o;
        return Objects.equals(historicoId, that.historicoId) &&
                Objects.equals(dependenteId, that.dependenteId) &&
                Objects.equals(horarioInicio, that.horarioInicio) &&
                Objects.equals(horarioFim, that.horarioFim) &&
                Objects.equals(mensagemStatus, that.mensagemStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historicoId, dependenteId, horarioInicio, horarioFim, mensagemStatus);
    }
}

