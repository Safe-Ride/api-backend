package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.mensagem.Mensagem;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.exception.DependenteNotLinkUsuarioException;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.MensagemRepository;

import java.util.Objects;
import java.util.Optional;

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
        payload.setLida(false);
        return repository.save(payload);
    }

    public void marcarLida(Integer id) {
        Optional<Mensagem> mensagemOpt = repository.findById(id);
        if(mensagemOpt.isEmpty()) throw new NotFoundException("Mensagem");

        Mensagem mensagem = mensagemOpt.get();

        mensagem.setLida(true);
        repository.save(mensagem);
    }
}
