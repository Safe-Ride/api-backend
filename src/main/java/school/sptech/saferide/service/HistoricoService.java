package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.historico.Historico;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.HistoricoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoService {

    private final HistoricoRepository repository;
    private final TrajetoService trajetoService;

    public Historico criar(Historico historico, Integer trajetoId) {
        Trajeto trajeto = trajetoService.listarPorId(trajetoId);
        historico.setTrajeto(trajeto);
        return repository.save(historico);
    }

    public Historico listarPorId(int historicoId) {
        Optional<Historico> optional = repository.findById(historicoId);
        if (optional.isEmpty()) throw new NotFoundException("Historico");
        return optional.get();
    }

    public List<Historico> listar() {
        return repository.findAll();
    }
}
