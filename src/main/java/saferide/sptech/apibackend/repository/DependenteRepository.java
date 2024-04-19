package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Dependente;

import java.util.List;

public interface DependenteRepository extends JpaRepository<Dependente, Integer> {
    List<Dependente> findByClienteId(int id);
}
