package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saferide.sptech.apibackend.dto.endereco.ViaCepResponse;
import saferide.sptech.apibackend.dto.rota.RotaMapper;
import saferide.sptech.apibackend.dto.rota.RotaResponse;
import saferide.sptech.apibackend.entity.Rota;
import saferide.sptech.apibackend.repository.RotaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository repository;

    private final ViaCepService viaCepService = new ViaCepService();

    public RotaResponse criar(Rota payload) throws IOException {
        Rota entity = repository.save(payload);
        ViaCepResponse viaCepResponse = viaCepService.getEndereco(entity.getEndereco().getCep());
        return RotaMapper.toDto(entity, viaCepResponse);
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
