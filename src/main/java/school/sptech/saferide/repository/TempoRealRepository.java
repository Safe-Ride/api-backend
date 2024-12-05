package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.entity.tempoReal.TempoReal;

import java.util.Optional;

@Repository
public interface TempoRealRepository extends JpaRepository<TempoReal, Integer> {
    @Query(value = """
        SELECT t.*
        FROM tempo_real t
        INNER JOIN dependente d on d.motorista_id = t.id_motorista
        WHERE d.id = :idDependente
          AND t.data = (
            SELECT MAX(t2.data)
            FROM tempo_real t2
            INNER JOIN dependente d2 ON d2.motorista_id = t2.id_motorista
            WHERE d2.id = :idDependente
          )
        """, nativeQuery = true)
    Optional<TempoReal> findLatestByDependente(@Param("idDependente") Integer idDependente);
}

