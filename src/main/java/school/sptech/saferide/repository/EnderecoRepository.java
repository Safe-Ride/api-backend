package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.endereco.Endereco;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findByUsuarioId(int usuarioId);
}
