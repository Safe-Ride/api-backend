package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saferide.sptech.apibackend.dto.mensagem.MensagemMapper;
import saferide.sptech.apibackend.dto.mensagem.MensagemRequest;
import saferide.sptech.apibackend.entity.Chat;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.repository.MensagemRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository repository;
    private final DependenteService dependenteService;

    public Mensagem criar(MensagemRequest request) {
        Dependente dependente = dependenteService.listarPorId(request.getDependenteId());
        Mensagem entity = MensagemMapper.toEntity(request, dependente);
        return repository.save(entity);
    }

}
