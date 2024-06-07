package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Rota;
import saferide.sptech.apibackend.entity.id.RotaId;

import java.util.List;

public interface RotaRepository extends JpaRepository<Rota, RotaId> {

    List<Rota> findByDependenteId(int dependenteId);

    List<Rota> findByTrajetoId(Integer trajetoId);

}
