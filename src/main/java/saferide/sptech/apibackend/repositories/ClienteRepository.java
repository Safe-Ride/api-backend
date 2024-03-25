package saferide.sptech.apibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
