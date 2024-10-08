package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.escola.Escola;

import java.util.Optional;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {
    Optional<Escola> findByNome(String nome);
}
