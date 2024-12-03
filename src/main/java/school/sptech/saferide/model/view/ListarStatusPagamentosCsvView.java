package school.sptech.saferide.model.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter

@Table(name = "detalhe_pagamento_do_motorista")
public class ListarStatusPagamentosCsvView {

@Column(name = "responsavel_nome")
    private String responsavelNome;
@Column(name = "responsavel_email")
    private String responsavelEmail;
@JsonFormat(pattern = "dd/MM/yyyy")
@Column(name = "pagamento_data_criacao")
    private LocalDateTime pagamentoDataCriacao;
@JsonFormat(pattern = "dd/MM/yyyy")
@Column(name = "pagamento_data_vencimento")
    private String pagamentoDataVencimento;
@Column(name = "pagamento_data_efetuacao")
    private LocalDateTime pagamentoDataEfetuacao;
@Column(name = "pagamento_tipo")
    private String pagamentoTipo;
@Column(name = "pagamento_valor")
    private Double pagamentoValor;
@Column(name= "pagamento_status")
    private String pagamentoStatus;
@Id
@Column(name = "motorista_id")
    private Integer motoristaId;
}
