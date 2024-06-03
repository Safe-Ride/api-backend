package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.escola.EscolaMapper;
import saferide.sptech.apibackend.dto.escola.EscolaRequestUpdate;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.repository.EscolaRepository;

import java.util.List;
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

    public List<Escola> listar() {
        List<Escola> escolas = repository.findAll();
        if (escolas.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return escolas;
    }

    public Escola listarPorId(int id) {
        Optional<Escola> escolaOpt = repository.findById(id);
        if (escolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return escolaOpt.get();
    }

    public Escola atualizar(int id, EscolaRequestUpdate request) {
        Optional<Escola> escolaOpt = repository.findById(id);
        if (escolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Escola entity = EscolaMapper.toEntityAtt(request, escolaOpt.get());
        return repository.save(entity);
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }
}
