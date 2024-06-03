package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saferide.sptech.apibackend.dto.endereco.ViaCepResponse;
import saferide.sptech.apibackend.dto.rota.RotaMapper;
import saferide.sptech.apibackend.dto.rota.RotaResponse;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Rota;
import saferide.sptech.apibackend.entity.Trajeto;
import saferide.sptech.apibackend.repository.RotaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        return repository.save(payload);
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

            rotaResponses.add(RotaMapper.toDto(rota, viaCepResponse));

        }

        return rotaResponses;
    }

}
