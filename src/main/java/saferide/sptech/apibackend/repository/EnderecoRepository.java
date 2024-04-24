package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
