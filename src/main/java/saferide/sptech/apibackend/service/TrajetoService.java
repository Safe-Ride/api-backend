package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.trajeto.TrajetoMapper;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequest;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequestUpdate;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Trajeto;
import saferide.sptech.apibackend.repository.EscolaRepository;
import saferide.sptech.apibackend.repository.TrajetoRepository;
import saferide.sptech.apibackend.service.utils.Ordenacao;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrajetoService {

    private final TrajetoRepository trajetoRepository;
    private final EscolaRepository escolaRepository;

    public Trajeto criar(TrajetoRequest request) {
        Optional<Escola> escolaOpt = escolaRepository.findById(request.getEscolaId());
        if (escolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Trajeto entity = TrajetoMapper.toEntity(request, escolaOpt.get());
        Trajeto saveTrajeto = trajetoRepository.save(entity);
        return saveTrajeto;
    }

    public List<Trajeto> listar() {
        List<Trajeto> trajetos = trajetoRepository.findAll();
        if (trajetos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return Ordenacao.quickSort(trajetos, 0, trajetos.size()-1);
    }

    public Trajeto listarPorId(int id) {
        Optional<Trajeto> trajetoOpt = trajetoRepository.findById(id);
        if (trajetoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return trajetoOpt.get();
    }

    public Trajeto atualizar(int id, TrajetoRequestUpdate request) {
        Optional<Trajeto> trajetoOpt = trajetoRepository.findById(id);
        if (trajetoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Trajeto entity = TrajetoMapper.toEntityAtt(request, trajetoOpt.get());
        Trajeto saveEndereco = trajetoRepository.save(entity);
        return saveEndereco;
    }

    public Void remover(int id) {
        if (!trajetoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        trajetoRepository.deleteById(id);
        return null;
    }
}
