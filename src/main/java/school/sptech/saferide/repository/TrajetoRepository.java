package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.trajeto.Trajeto;

import java.util.List;

public interface TrajetoRepository extends JpaRepository<Trajeto, Integer> {
    @Query("SELECT t FROM Trajeto t " +
            "JOIN FETCH t.motorista m " +
            "JOIN FETCH t.escola e " +
            "LEFT JOIN FETCH t.rotas r " +
            "WHERE m.id = :motoristaId " +
            "ORDER BY t.ativo DESC, t.id")
    List<Trajeto> findTrajetosByMotoristaId(@Param("motoristaId") int motoristaId);
}
