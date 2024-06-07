package saferide.sptech.apibackend.entity.view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "v_pagamento_status")
public class PagamentoStatusView {

    @Id
    private Integer pago;
    private Integer pendente;
    private Integer atrasado;

}
