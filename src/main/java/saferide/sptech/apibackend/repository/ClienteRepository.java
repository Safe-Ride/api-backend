package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
