package saferide.sptech.apibackend.service.utils;

import lombok.*;
import saferide.sptech.apibackend.entity.Rota;
import saferide.sptech.apibackend.entity.Status;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Operacao {

    private String tipo;
    private Rota rota;
    private Status statusAlterado;

}
