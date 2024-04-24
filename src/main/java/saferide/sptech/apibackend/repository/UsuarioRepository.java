package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
