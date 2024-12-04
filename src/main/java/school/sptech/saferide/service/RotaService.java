package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.rota.RotaRequest;
import school.sptech.saferide.model.entity.rota.RotaEscolaEndereco;
import school.sptech.saferide.model.entity.rota.RotaListarEnderecos;
import school.sptech.saferide.model.entity.rota.RotaUpdateRequest;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import school.sptech.saferide.model.enums.StatusDependente;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.RotaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository repository;
    private final EnderecoService enderecoService;
    private final DependenteService dependenteService;
    private final TrajetoService trajetoService;
  
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
        rota.setHorario(LocalDateTime.now());
        return repository.save(rota);
    }

    public Rota criar(RotaRequest request) {
        Rota rota = new Rota();
        Endereco endereco = enderecoService.listarPorId(request.getEnderecoId());
        Dependente dependente = dependenteService.listarPorId(request.getDependenteId());
        Trajeto trajeto = trajetoService.listarPorId(request.getTrajetoId());

        rota.setEndereco(endereco);
        rota.setDependente(dependente);
        rota.setTrajeto(trajeto);
        rota.setStatus(StatusDependente.NAO_INICIADO);
        return repository.save(rota);
    }

    public void remover(int id) {
        Optional<Rota> rota = repository.findById(id);
        if(rota.isEmpty()) throw new NotFoundException("Rota");
        repository.removeById(id);
    }

    public Rota criar(Trajeto trajeto, Dependente dependente, Endereco endereco) {
        Rota rota = new Rota();

        rota.setEndereco(endereco);
        rota.setDependente(dependente);
        rota.setTrajeto(trajeto);
        rota.setStatus(StatusDependente.NAO_INICIADO);
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