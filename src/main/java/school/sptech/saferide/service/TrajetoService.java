package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import school.sptech.saferide.model.entity.trajeto.TrajetoMapper;
import school.sptech.saferide.model.entity.trajeto.TrajetoResponse;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.TrajetoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Trajeto listarPorId(int id) {
        Optional<Trajeto> trajetoOpt = repository.findById(id);
        if (trajetoOpt.isEmpty()) throw new NotFoundException("Trajeto");
        return trajetoOpt.get();
    }

    public List<TrajetoResponse> listarTrajetosPorMotorista(Integer motoristaId) {
        List<Trajeto> trajetos = repository.findTrajetosByMotoristaId(motoristaId);

        return trajetos.stream()
                .map(TrajetoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Trajeto atualizarAtivo(int trajetoId, Boolean ativo) {
        Trajeto trajeto = listarPorId(trajetoId);
        trajeto.setAtivo(ativo);
        return repository.save(trajeto);
    }

    public Void remover(int id) {
        listarPorId(id);
        repository.deleteById(id);
        return null;
    }
}
