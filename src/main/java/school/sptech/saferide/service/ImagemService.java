package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.imagem.Imagem;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.ImagemRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImagemService {

    private final ImagemRepository repository;

    public Imagem criar(Imagem payload) {
        return repository.save(payload);
    }

    public Imagem listarPorId(int id) {
        Optional<Imagem> imagemOpt = repository.findById(id);
        if (imagemOpt.isEmpty()) throw new NotFoundException("Imagem");
        return imagemOpt.get();
    }

    public Void remover(int id) {
        listarPorId(id);
        repository.deleteById(id);
        return null;
    }
}
