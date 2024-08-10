package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.mensagem.Mensagem;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.exception.DependenteNotLinkUsuarioException;
import school.sptech.saferide.repository.MensagemRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository repository;
    private final ConversaService conversaService;
    private final UsuarioService usuarioService;
    private final DependenteService dependenteService;

    public Mensagem criar(
            Mensagem payload,
            int conversaId,
            int usuarioId,
            int dependenteId) {
        Conversa conversa = conversaService.listarPorId(conversaId);
        Usuario usuario = usuarioService.listarPorId(usuarioId);
        Dependente dependente = dependenteService.listarPorId(dependenteId);

        if (usuario.getDependentes().stream()
                .noneMatch(u -> Objects.equals(u.getId(), dependente.getId())))
            throw new DependenteNotLinkUsuarioException(dependente.getNome(), usuario.getNome());

        payload.setConversa(conversa);
        payload.setUsuario(usuario);
        payload.setDependente(dependente);
        return repository.save(payload);
    }
}
