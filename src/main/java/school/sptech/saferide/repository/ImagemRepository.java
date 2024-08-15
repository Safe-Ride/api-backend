package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.imagem.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
}
