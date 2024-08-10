package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.transporte.Transporte;

public interface TransporteRepository extends JpaRepository<Transporte, Integer> {
}
