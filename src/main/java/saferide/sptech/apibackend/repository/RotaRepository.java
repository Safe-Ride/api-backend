package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Rota;
import saferide.sptech.apibackend.entity.id.RotaId;

import java.util.List;
import java.util.Optional;

public interface RotaRepository extends JpaRepository<Rota, RotaId> {

    List<Rota> findByDependenteId(int dependenteId);
}
