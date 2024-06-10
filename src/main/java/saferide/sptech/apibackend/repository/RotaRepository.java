package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Rota;

import java.util.List;
import java.util.Optional;

public interface RotaRepository extends JpaRepository<Rota, Integer> {

    List<Rota> findByDependenteId(int dependenteId);

    List<Rota> findByTrajetoId(Integer trajetoId);

    Optional<Rota> findByTrajetoIdAndDependenteIdAndEnderecoId(int trajetoId, int dependenteId, int enderecoId);

}
