package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.entity.tempoReal.TempoReal;

import java.util.Optional;

@Repository
public interface TempoRealRepository extends JpaRepository<TempoReal, Integer> {
    @Query("SELECT t FROM TempoReal t WHERE t.id.data = (SELECT MAX(t2.id.data) FROM TempoReal t2 WHERE t2.id.idMotorista = :idMotorista)")
    Optional<TempoReal> findLatestByMotorista(@Param("idMotorista") Integer idMotorista);
}
