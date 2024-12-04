package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.rota.RotaEscolaEndereco;
import school.sptech.saferide.model.entity.rota.RotaListarEnderecos;
import school.sptech.saferide.model.entity.rota.RotaUpdateRequest;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import school.sptech.saferide.model.enums.StatusDependente;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.RotaRepository;

import java.util.Arrays;
import java.util.List;
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
    public Rota criar(Trajeto trajeto, Dependente dependente, Endereco endereco) {
        return repository.save(new Rota(null, trajeto, dependente, endereco, StatusDependente.NAO_INICIADO));
    }

    public Rota listarPorId(int id) {
        Optional<Rota> rotaOpt = repository.findById(id);
        if (rotaOpt.isEmpty()) throw new NotFoundException("Rota");
        return rotaOpt.get();
    }

    public Optional<Rota> listarPorTrajetoDependenteEndereco(int trajetoId, int dependenteId, int enderecoId) {
        return repository.findByTrajetoIdAndDependenteIdAndEnderecoId(trajetoId, dependenteId, enderecoId);
    }

    public Rota atualizar(int id, RotaUpdateRequest request) {
        Rota rota = listarPorId(id);
        rota.setStatus(request.getStatus());
        return repository.save(rota);
    }

    public List<RotaListarEnderecos> listarEnderecosPeloTrajeto(int idTrajeto) {
      return repository.listarParadaPorTrajeto(idTrajeto);
    }

    public RotaEscolaEndereco listarEscolaEndereco(int idTrajeto) {
        List<Object[]> resultado = repository.listarEnderecoEscolaPorTrajeto(idTrajeto);

        if (resultado.isEmpty()) {
            throw new NotFoundException("Rota não encontrada");
        }

        Object[] dados = resultado.get(0);
        if (dados.length != 2) {
            throw new NotFoundException("Dados inválidos");
        }

        Double latitude = (dados[0] != null) ? (Double) dados[0] : null;
        Double longitude = (dados[1] != null) ? (Double) dados[1] : null;

        return new RotaEscolaEndereco(latitude, longitude);
    }

}
