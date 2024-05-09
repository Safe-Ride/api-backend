package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.chat.ChatMapper;
import saferide.sptech.apibackend.dto.chat.ChatRequest;
import saferide.sptech.apibackend.dto.transporte.TransporteMapper;
import saferide.sptech.apibackend.dto.transporte.TransporteRequest;
import saferide.sptech.apibackend.dto.transporte.TransporteRequestUpdate;
import saferide.sptech.apibackend.entity.Chat;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Transporte;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.repository.ChatRepository;
import saferide.sptech.apibackend.repository.MensagemRepository;
import saferide.sptech.apibackend.repository.TransporteRepository;
import saferide.sptech.apibackend.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository repository;

    public Chat criar(ChatRequest request) {
        Chat entity = ChatMapper.toEntity(request);
        return repository.save(entity);
    }

    public Chat listar(
            int responsavelId,
            int motoristaId) {
        Optional<Chat> chatOpt = repository.findByResponsavelIdAndMotoristaId(responsavelId, motoristaId);
        if (chatOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return chatOpt.get();
    }

    public Chat listarPorId(int id) {
        Optional<Chat> chatOpt = repository.findById(id);
        if (chatOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return chatOpt.get();
    }

}
