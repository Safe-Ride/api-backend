package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Historico;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.MensagemRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository repository;
    private final HistoricoService historicoService;
    private final UsuarioService usuarioService;
    private final DependenteService dependenteService;

    public Mensagem criar(
            Mensagem payload,
            int historicoId,
            int usuarioId,
            int dependenteId) {
        Historico historico = historicoService.listarPorId(historicoId);
        Usuario usuario = usuarioService.listarPorId(usuarioId);
        Dependente dependente = dependenteService.listarPorId(dependenteId);

        if (usuario.getDependentes().stream()
                .noneMatch(u -> Objects.equals(u.getId(), dependente.getId())))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        payload.setHistorico(historico);
        payload.setUsuario(usuario);
        payload.setDependente(dependente);
        return repository.save(payload);
    }

}
