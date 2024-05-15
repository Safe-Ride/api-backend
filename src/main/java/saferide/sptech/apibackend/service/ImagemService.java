package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.imagem.ImagemMapper;
import saferide.sptech.apibackend.dto.imagem.ImagemRequest;
import saferide.sptech.apibackend.entity.Imagem;
import saferide.sptech.apibackend.repository.ImagemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImagemService {

    private final ImagemRepository repository;

    public Imagem criar(ImagemRequest request) {
        Imagem entity = ImagemMapper.toEntity(request);
        return repository.save(entity);
    }

    public List<Imagem> listar() {
        List<Imagem> imagems = repository.findAll();
        if (imagems.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return imagems;
    }

    public Imagem listarPorId(int id) {
        Optional<Imagem> imagemOpt = repository.findById(id);
        if (imagemOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return imagemOpt.get();
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }
}
