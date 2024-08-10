package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.pagamento.Pagamento;
import school.sptech.saferide.model.view.PagamentoStatusView;
import school.sptech.saferide.model.view.PagamentosTotalEfetuadosView;
import school.sptech.saferide.model.view.RendaBrutaMesView;
import school.sptech.saferide.repository.PagamentoRepository;
import school.sptech.saferide.repository.view.PagamentoStatusViewRepository;
import school.sptech.saferide.repository.view.PagamentosTotalEfetuadosViewRepository;
import school.sptech.saferide.repository.view.RendaBrutaMesViewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;
    private final PagamentoStatusViewRepository pagamentoStatusView;
    private final RendaBrutaMesViewRepository rendaBrutaMesViewRepository;
    private final PagamentosTotalEfetuadosViewRepository pagamentosTotalEfetuadosViewRepository;

    public Pagamento criar(Pagamento payload) {
        return repository.save(payload);
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
