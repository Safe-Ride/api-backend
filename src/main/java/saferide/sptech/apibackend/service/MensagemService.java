package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.mensagem.MensagemMapper;
import saferide.sptech.apibackend.dto.mensagem.MensagemRequest;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.MensagemRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository repository;
    private final DependenteService dependenteService;
    private final UsuarioService usuarioService;

    public Mensagem criar(MensagemRequest request) {
        Dependente dependente = dependenteService.listarPorId(request.getDependenteId());
        Usuario usuario = usuarioService.listarPorId(request.getUsuarioId());

        if (usuario.getDependentes().stream()
                .noneMatch(u -> Objects.equals(u.getId(), dependente.getId())))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Mensagem entity = MensagemMapper.toEntity(request, dependente);
        return repository.save(entity);
    }

}
