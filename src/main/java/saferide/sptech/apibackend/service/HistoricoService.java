package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.entity.Historico;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.HistoricoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoService {

    private final HistoricoRepository repository;
    private final UsuarioService usuarioService;

    public Historico criar(
            Historico payload,
            int responsavelId,
            int motoristaId) {
        Usuario responsavel = usuarioService.listarPorId(responsavelId);
        Usuario motorista = usuarioService.listarPorId(motoristaId);
        payload.setResponsavel(responsavel);
        payload.setMotorista(motorista);
        return repository.save(payload);
    }

    public Historico listar(
            int responsavelId,
            int motoristaId) {
        Optional<Historico> historicoOpt = repository.findByResponsavelIdAndMotoristaId(responsavelId, motoristaId);
        if (historicoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return historicoOpt.get();
    }

    public Historico listarPorId(int id) {
        Optional<Historico> historicoOpt = repository.findById(id);
        if (historicoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return historicoOpt.get();
    }

    public List<Historico> listarPorResponsavel(int responsavelId) {
        List<Historico> historicos = repository.findByResponsavelId(responsavelId);
        if (historicos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        // Filtrar para receber só a ultima mensagem
        return historicos;
    }

    public List<Historico> listarPorMotorista(int motoristaId) {
        List<Historico> historicos = repository.findByMotoristaId(motoristaId);
        if (historicos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        // Filtrar para receber só a ultima mensagem
        return historicos;
    }

}
