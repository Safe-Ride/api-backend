package saferide.sptech.apibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.model.Trajeto;
import saferide.sptech.apibackend.model.Transporte;

public interface TransporteRepository extends JpaRepository<Transporte, Integer> {

}
