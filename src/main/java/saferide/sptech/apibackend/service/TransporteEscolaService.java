package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Transporte;
import saferide.sptech.apibackend.entity.TransporteEscola;
import saferide.sptech.apibackend.repository.TransporteEscolaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransporteEscolaService {

    private final TransporteEscolaRepository repository;
    private final TransporteService transporteService;
    private final EscolaService escolaService;

    public TransporteEscola criar(TransporteEscola payload, int transporteId, int escolaId) {
        Transporte transporte = transporteService.listarPorId(transporteId);
        Escola escola = escolaService.listarPorId(escolaId);

        payload.setTransporte(transporte);
        payload.setEscola(escola);
        return repository.save(payload);
    }

    public List<TransporteEscola> listar() {
        List<TransporteEscola> transportesEscolas = repository.findAll();
        if (transportesEscolas.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return transportesEscolas;
    }

    public TransporteEscola listarPorId(int id) {
        Optional<TransporteEscola> transporteEscolaOpt = repository.findById(id);
        if (transporteEscolaOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return transporteEscolaOpt.get();
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }
}
