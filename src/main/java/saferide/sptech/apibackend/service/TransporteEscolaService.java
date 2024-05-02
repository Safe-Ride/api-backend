package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.transporteEscola.TransporteEscolaMapper;
import saferide.sptech.apibackend.dto.transporteEscola.TransporteEscolaRequest;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Transporte;
import saferide.sptech.apibackend.entity.TransporteEscola;
import saferide.sptech.apibackend.repository.EscolaRepository;
import saferide.sptech.apibackend.repository.TransporteEscolaRepository;
import saferide.sptech.apibackend.repository.TransporteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransporteEscolaService {

    private final TransporteEscolaRepository transporteEscolaRepository;
    private final TransporteRepository transporteRepository;
    private final EscolaRepository escolaRepository;

    public TransporteEscola criar(TransporteEscolaRequest request) {
        Optional<Transporte> transporteOpt = transporteRepository.findById(request.getTransporteId());
        if (transporteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Optional<Escola> escolaOpt = escolaRepository.findById(request.getEscolaId());
        if (escolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        TransporteEscola entity = TransporteEscolaMapper.toEntity(request);
        TransporteEscola saveTransporteEscola = transporteEscolaRepository.save(entity);
        return saveTransporteEscola;
    }

    public List<TransporteEscola> listar() {
        List<TransporteEscola> transportesEscolas = transporteEscolaRepository.findAll();
        if (transportesEscolas.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return transportesEscolas;
    }

    public TransporteEscola listarPorId(int id) {
        Optional<TransporteEscola> transporteEscolaOpt = transporteEscolaRepository.findById(id);
        if (transporteEscolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return transporteEscolaOpt.get();
    }

    public Void remover(int id) {
        if (!transporteEscolaRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        transporteEscolaRepository.deleteById(id);
        return null;
    }
}
