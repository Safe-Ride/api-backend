package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Integer> {
}
