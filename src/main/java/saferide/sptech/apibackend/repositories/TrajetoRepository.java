package saferide.sptech.apibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.model.Endereco;
import saferide.sptech.apibackend.model.Trajeto;

public interface TrajetoRepository extends JpaRepository<Trajeto, Integer> {

}
