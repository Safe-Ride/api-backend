package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.escola.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {
}
