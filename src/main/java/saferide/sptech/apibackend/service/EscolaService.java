package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.escola.EscolaMapper;
import saferide.sptech.apibackend.dto.escola.EscolaRequest;
import saferide.sptech.apibackend.dto.escola.EscolaRequestUpdate;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.repository.EnderecoRepository;
import saferide.sptech.apibackend.repository.EscolaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EscolaService {

    private final EscolaRepository escolaRepository;
    private final EnderecoRepository enderecoRepository;

    public Escola criar(EscolaRequest request) {
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(request.getEnderecoId());
        if (enderecoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Escola entity = EscolaMapper.toEntity(request, enderecoOpt.get());
        Escola saveTrajeto = escolaRepository.save(entity);
        return saveTrajeto;
    }

    public List<Escola> listar() {
        List<Escola> escolas = escolaRepository.findAll();
        if (escolas.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return escolas;
    }

    public Escola listarPorId(int id) {
        Optional<Escola> escolaOpt = escolaRepository.findById(id);
        if (escolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return escolaOpt.get();
    }

    public Escola atualizar(int id, EscolaRequestUpdate request) {
        Optional<Escola> escolaOpt = escolaRepository.findById(id);
        if (escolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Escola entity = EscolaMapper.toEntityAtt(request, escolaOpt.get());
        Escola saveEndereco = escolaRepository.save(entity);
        return saveEndereco;
    }

    public Void remover(int id) {
        if (!escolaRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        escolaRepository.deleteById(id);
        return null;
    }
}
