package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.transporte.Transporte;
import school.sptech.saferide.model.entity.transporteEscola.TransporteEscola;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.TransporteEscolaRepository;

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

    public TransporteEscola listarPorId(int id) {
        Optional<TransporteEscola> transporteEscolaOpt = repository.findById(id);
        if (transporteEscolaOpt.isEmpty()) throw new NotFoundException("TransporteEscola");
        return transporteEscolaOpt.get();
    }

    public List<TransporteEscola> listarPorTransporte(int transporteId) {
        List<TransporteEscola> transporteEscolas = repository.findByTransporteId(transporteId);
        if (transporteEscolas.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return transporteEscolas;
    }

    public List<TransporteEscola> listarPorEscola(int escolaId) {
        List<TransporteEscola> transporteEscolas = repository.findByEscolaId(escolaId);
        if (transporteEscolas.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return transporteEscolas;
    }
}
