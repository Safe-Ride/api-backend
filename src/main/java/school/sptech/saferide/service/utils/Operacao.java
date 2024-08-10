package school.sptech.saferide.service.utils;

import lombok.*;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.enums.StatusDependente;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operacao {

    private String tipo;
    private Rota rota;
    private StatusDependente statusAlterado;

}
