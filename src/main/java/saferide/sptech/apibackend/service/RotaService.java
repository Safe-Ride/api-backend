package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.endereco.ViaCepResponse;
import saferide.sptech.apibackend.dto.rota.RotaMapper;
import saferide.sptech.apibackend.dto.rota.RotaResponse;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.repository.RotaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository repository;
    private final TrajetoService trajetoService;
    private final DependenteService dependenteService;
    private final EnderecoService enderecoService;

    private final ViaCepService viaCepService = new ViaCepService();

    public Rota criar(Rota payload, int trajetoId, int dependenteId, int enderecoId) {
        Trajeto trajeto = trajetoService.listarPorId(trajetoId);
        Dependente dependente = dependenteService.listarPorId(dependenteId);
        Endereco endereco = enderecoService.listarPorId(enderecoId);

        payload.setTrajeto(trajeto);
        payload.setDependente(dependente);
        payload.setEndereco(endereco);
        payload.setStatus(Status.NAO_INICIADO);
        return repository.save(payload);
    }

    public Rota listarPorId(int id) {
        Optional<Rota> opt = repository.findById(id);
        if (opt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return opt.get();
    }

    public List<Rota> listarPorTrajeto(int id) {
        List<Rota> list = repository.findByTrajetoId(id);
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return list;
    }

    public Rota listarPorComponentes(int trajetoId, int dependenteId, int enderecoId) {
        Optional<Rota> opt = repository.findByTrajetoIdAndDependenteIdAndEnderecoId(trajetoId, dependenteId, enderecoId);
        if (opt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return opt.get();
    }

    public List<RotaResponse> listarRotasPorDependente(int dependenteId) {
        List<Rota> rotas = repository.findByDependenteId(dependenteId);

        List<RotaResponse> rotaResponses = new ArrayList<>();

        for (Rota rota : rotas) {
            ViaCepResponse viaCepResponse = new ViaCepResponse();
            try {
                viaCepResponse = viaCepService.getEndereco(rota.getEndereco().getCep());
            } catch (Exception e) {
                e.printStackTrace();
            }

            rotaResponses.add(RotaMapper.toDtoViaCep(rota, viaCepResponse));

        }

        return rotaResponses;
    }

    public Rota atualizarStatusPorId(int id, Status status) {
        Rota rota = listarPorId(id);
        rota.setStatus(status);
        return repository.save(rota);
    }

}
