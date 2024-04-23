package saferide.sptech.apibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.endereco.EnderecoMapper;
import saferide.sptech.apibackend.dto.endereco.EnderecoRequest;
import saferide.sptech.apibackend.dto.endereco.EnderecoRequestUpdate;
import saferide.sptech.apibackend.dto.endereco.EnderecoResponse;
import saferide.sptech.apibackend.dto.trajeto.TrajetoMapper;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequest;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequestUpdate;
import saferide.sptech.apibackend.dto.trajeto.TrajetoResponse;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Trajeto;
import saferide.sptech.apibackend.repository.EnderecoRepository;
import saferide.sptech.apibackend.repository.TrajetoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetoService {

    private static TrajetoRepository trajetoRepository;

    @Autowired
    public TrajetoService(TrajetoRepository trajetoRepository) {
        this.trajetoRepository = trajetoRepository;
    }

    public TrajetoResponse criar(TrajetoRequest request) {
        Trajeto entity = TrajetoMapper.toEntity(request);
        Trajeto saveTrajeto = trajetoRepository.save(entity);
        return TrajetoMapper.toDto(saveTrajeto);
    }

    public List<TrajetoResponse> listar() {
        List<Trajeto> trajetos = trajetoRepository.findAll();
        if (trajetos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return TrajetoMapper.toDto(trajetos);
    }

    public TrajetoResponse listarPorId(int id) {
        Optional<Trajeto> trajetoOpt = trajetoRepository.findById(id);
        if (trajetoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return TrajetoMapper.toDto(trajetoOpt.get());
    }

    public TrajetoResponse atualizar(int id, TrajetoRequestUpdate request) {
        Optional<Trajeto> trajetoOpt = trajetoRepository.findById(id);
        if (trajetoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Trajeto entity = TrajetoMapper.toEntityAtt(request, trajetoOpt.get());
        Trajeto saveEndereco = trajetoRepository.save(entity);
        return TrajetoMapper.toDto(saveEndereco);
    }

    public Void remover(int id) {
        if (!trajetoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        trajetoRepository.deleteById(id);
        return null;
    }
}
