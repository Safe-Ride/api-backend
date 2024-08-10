package school.sptech.saferide.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
