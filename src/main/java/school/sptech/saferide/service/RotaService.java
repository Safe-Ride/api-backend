package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.rota.RotaUpdateRequest;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.RotaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository repository;
    private final TrajetoService trajetoService;
    private final DependenteService dependenteService;
    private final EnderecoService enderecoService;

    public Rota criar(Rota rota, Integer trajetoId, Integer dependenteId, Integer enderecoId) {
        rota.setTrajeto(trajetoService.listarPorId(trajetoId));
        rota.setDependente(dependenteService.listarPorId(dependenteId));
        rota.setEndereco(enderecoService.listarPorId(enderecoId));
        return repository.save(rota);
    }
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

}
