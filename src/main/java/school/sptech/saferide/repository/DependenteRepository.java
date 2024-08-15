package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.dependente.Dependente;

import java.util.List;
import java.util.Optional;

public interface DependenteRepository extends JpaRepository<Dependente, Integer> {
    List<Dependente> findByResponsavelId(int id);
    Optional<Dependente> findByIdAndMotoristaId(int id, int motoristaId);
}
