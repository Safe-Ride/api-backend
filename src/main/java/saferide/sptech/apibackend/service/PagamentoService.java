package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.entity.Pagamento;
import saferide.sptech.apibackend.entity.SituacaoPagamento;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.view.PagamentoStatusView;
import saferide.sptech.apibackend.entity.view.PagamentosTotalEfetuadosView;
import saferide.sptech.apibackend.entity.view.RendaBrutaMesView;
import saferide.sptech.apibackend.repository.PagamentoRepository;
import saferide.sptech.apibackend.repository.view.PagamentoStatusViewRepository;
import saferide.sptech.apibackend.repository.view.PagamentosTotalEfetuadosViewRepository;
import saferide.sptech.apibackend.repository.view.RendaBrutaMesViewRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;
    private final UsuarioService usuarioService;
    private final PagamentoStatusViewRepository pagamentoStatusView;
    private final RendaBrutaMesViewRepository rendaBrutaMesViewRepository;
    private final PagamentosTotalEfetuadosViewRepository pagamentosTotalEfetuadosViewRepository;

    public List<Pagamento> criarContrato(Pagamento payload, int cobradorId, int pagadorId) {
        int mes = 0;
        int ano = 0;
        List<Pagamento> pagamentos = new ArrayList<>();

        Optional<LocalDate> ultimaCobranca = repository.findLastDataCriacaoByPagadorId(pagadorId);
        mes = ultimaCobranca
                .map(date -> date.getMonth().ordinal() + 1)
                .orElseGet(() -> LocalDate.now().getMonth().ordinal() + 1);
        ano = ultimaCobranca
                .map(LocalDate::getYear)
                .orElseGet(() -> LocalDate.now().getYear());

        Usuario cobrador = usuarioService.listarPorId(cobradorId);
        Usuario pagador = usuarioService.listarPorId(pagadorId);

        for (int i = mes; i <= 12; i++) {
            Pagamento pagamento = criar(payload, cobrador, pagador, i, ano);
            pagamentos.add(pagamento);
        }

        return pagamentos;
    }

    public Pagamento criar(Pagamento payload, Usuario cobrador, Usuario pagador, int mes, int ano) {
        Pagamento pagamento = new Pagamento();
        pagamento.setCobrador(cobrador);
        pagamento.setPagador(pagador);
        pagamento.setDataCriacao(LocalDate.parse("%d-%02d-01".formatted(ano, mes)));
        pagamento.setValor(payload.getValor());
        pagamento.setTipo(payload.getTipo());
        pagamento.setSituacao(SituacaoPagamento.PENDENTE);
        return repository.save(pagamento);
    }

    public List<Pagamento> listarPorMotorista() {
        List<Pagamento> list = repository.findAll();
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return list;
    }

    public List<Pagamento> listarPorResponsavel() {
        List<Pagamento> list = repository.findAll();
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return list;
    }

    public PagamentoStatusView listarPagamentoStatusView() {
        return pagamentoStatusView.findAll().get(0);
    }

    public List<RendaBrutaMesView> listarRendaBrutaMesView() {
        return rendaBrutaMesViewRepository.findAll();
    }

    public List<PagamentosTotalEfetuadosView> listarPagamentosTotalEfetuadosView() {
        return pagamentosTotalEfetuadosViewRepository.findAll();
    }

}
