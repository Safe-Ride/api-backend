package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.trajeto.Trajeto;

public interface TrajetoRepository extends JpaRepository<Trajeto, Integer> {
}
