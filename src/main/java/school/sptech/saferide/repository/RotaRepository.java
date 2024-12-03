package school.sptech.saferide.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.rota.Rota;

import java.util.List;
import java.util.Optional;

public interface RotaRepository extends JpaRepository<Rota, Integer> {
    List<Rota> findByDependenteId(int dependenteId);
    List<Rota> findByTrajetoId(Integer trajetoId);
    Optional<Rota> findByTrajetoIdAndDependenteIdAndEnderecoId(int trajetoId, int dependenteId, int enderecoId);
    @Transactional
    void removeById(int id);
}
