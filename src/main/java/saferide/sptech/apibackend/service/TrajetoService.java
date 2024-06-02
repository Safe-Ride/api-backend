package saferide.sptech.apibackend.service;

import  lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.trajeto.TrajetoMapper;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequestUpdate;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Trajeto;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.TrajetoRepository;
import saferide.sptech.apibackend.service.utils.Ordenacao;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrajetoService {

    private final TrajetoRepository repository;
    private final EscolaService escolaService;
    private final UsuarioService usuarioService;

    public Trajeto criar(
            Trajeto payload,
            int motoristaId,
            int escolaId) {
        Usuario motorista = usuarioService.listarPorId(motoristaId);
        Escola escola = escolaService.listarPorId(escolaId);
        payload.setMotorista(motorista);
        payload.setEscola(escola);
        return repository.save(payload);
    }

    public List<Trajeto> listar() {
        List<Trajeto> trajetos = repository.findAll();
        if (trajetos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return Ordenacao.quickSort(trajetos, 0, trajetos.size()-1);
    }

    public Trajeto listarPorId(int id) {
        Optional<Trajeto> trajetoOpt = repository.findById(id);
        if (trajetoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return trajetoOpt.get();
    }

    public Trajeto atualizar(int id, TrajetoRequestUpdate request) {
        Optional<Trajeto> trajetoOpt = repository.findById(id);
        if (trajetoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Trajeto entity = TrajetoMapper.toEntityAtt(request, trajetoOpt.get());
        return repository.save(entity);
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }

}
