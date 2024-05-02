package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.transporte.TransporteMapper;
import saferide.sptech.apibackend.dto.transporte.TransporteRequest;
import saferide.sptech.apibackend.dto.transporte.TransporteRequestUpdate;
import saferide.sptech.apibackend.entity.Transporte;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.TransporteRepository;
import saferide.sptech.apibackend.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransporteService {

    private final TransporteRepository transporteRepository;
    private final UsuarioRepository usuarioRepository;

    public Transporte criar(TransporteRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(request.getUsuarioId());
        if (usuarioOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Transporte entity = TransporteMapper.toEntity(request, usuarioOpt.get());
        Transporte saveTransporte = transporteRepository.save(entity);
        return saveTransporte;
    }

    public List<Transporte> listar() {
        List<Transporte> transportes = transporteRepository.findAll();
        if (transportes.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return transportes;
    }

    public Transporte listarPorId(int id) {
        Optional<Transporte> transporteOpt = transporteRepository.findById(id);
        if (transporteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return transporteOpt.get();
    }

    public Transporte atualizar(int id, TransporteRequestUpdate request) {
        Optional<Transporte> transporteOpt = transporteRepository.findById(id);
        if (transporteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Transporte entity = TransporteMapper.toEntityAtt(request, transporteOpt.get());
        Transporte saveTransporte = transporteRepository.save(entity);
        return saveTransporte;
    }

    public Void remover(int id) {
        if (!transporteRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        transporteRepository.deleteById(id);
        return null;
    }
}
