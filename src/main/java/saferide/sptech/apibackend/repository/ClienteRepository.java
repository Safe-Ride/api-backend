package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Cliente;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Endereco;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByEnderecoId(int id);

}
