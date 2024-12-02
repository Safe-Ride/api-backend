package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.rota.RotaRequest;
import school.sptech.saferide.model.entity.rota.RotaUpdateRequest;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.RotaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository repository;

    public Rota listarPorId(int id) {
        Optional<Rota> rotaOpt = repository.findById(id);
        if (rotaOpt.isEmpty()) throw new NotFoundException("Rota");
        return rotaOpt.get();
    }

    public Rota atualizar(int id, RotaUpdateRequest request) {
        Rota rota = listarPorId(id);
        rota.setStatus(request.getStatus());
        return repository.save(rota);
    }

    public Rota criar(Rota request) {
        return repository.save(request);
    }

    public void remover(int id) {
        Optional<Rota> rota = repository.findById(id);
        if(rota.isEmpty()) throw new NotFoundException("Rota");
        repository.remove(rota.get());
    }
}
