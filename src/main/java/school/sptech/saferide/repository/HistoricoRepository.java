package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.entity.historico.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
}
