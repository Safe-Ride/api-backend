package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.endereco.Endereco;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.EscolaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EscolaService {

    private final EscolaRepository repository;
    private final EnderecoService enderecoService;

    public Escola criar(Escola payload, int enderecoId) {
        Endereco endereco = enderecoService.listarPorId(enderecoId);
        payload.setEndereco(endereco);
        return repository.save(payload);
    }

    public Escola listarPorId(int id) {
        Optional<Escola> escolaOpt = repository.findById(id);
        if (escolaOpt.isEmpty()) throw new NotFoundException("Escola");
        return escolaOpt.get();
    }

    public Void remover(int id) {
        listarPorId(id);
        repository.deleteById(id);
        return null;
    }
}
