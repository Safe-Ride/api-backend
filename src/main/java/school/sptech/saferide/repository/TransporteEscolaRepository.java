package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.transporteEscola.TransporteEscola;

import java.util.List;

public interface TransporteEscolaRepository extends JpaRepository<TransporteEscola, Integer> {
    List<TransporteEscola> findByTransporteId(int transporteId);
    List<TransporteEscola> findByEscolaId(int escolaId);
}
