package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.conversa.ConversaMapper;
import school.sptech.saferide.model.entity.conversa.ListarConversasMotorista;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.TipoUsuario;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.ConversaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversaService {

    private final ConversaRepository repository;
    private final UsuarioService usuarioService;

    public Conversa criar(
            Conversa payload,
            int responsavelId,
            int motoristaId) {
        Usuario responsavel = usuarioService.listarPorId(responsavelId);
        Usuario motorista = usuarioService.listarPorId(motoristaId);
        payload.setResponsavel(responsavel);
        payload.setMotorista(motorista);
        return repository.save(payload);
    }

    public Conversa listarEntreMotoristaEResponsavel(
            int responsavelId,
            int motoristaId) {
        Optional<Conversa> conversaOpt = repository.findByResponsavelIdAndMotoristaId(responsavelId, motoristaId);
        if (conversaOpt.isEmpty()) throw new NotFoundException("Conversa");
        return conversaOpt.get();
    }

    public Conversa listarPorId(int id) {
        Optional<Conversa> conversaOpt = repository.findById(id);
        if (conversaOpt.isEmpty()) throw new NotFoundException("Conversa");
        return conversaOpt.get();
    }

    public List<ListarConversasMotorista> listarConversasMotoristasPorResponsavel(int id) {
        return repository.findByResponsavelId(id);
    }
}
