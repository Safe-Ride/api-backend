package school.sptech.saferide.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.usuario.MotoristaListarClientes;
import school.sptech.saferide.model.entity.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String username);

    @Query("SELECT DISTINCT r.id AS id, r.nome AS nome, r.imagem.caminho " +
            "FROM Dependente d JOIN d.responsavel r " +
            "WHERE d.motorista.id = :motoristaId")
    List<Object[]> findResponsaveisByMotoristaId(@Param("motoristaId") int motoristaId);

    @Query("SELECT DISTINCT d.motorista FROM Dependente d WHERE d.responsavel.id = :responsavelId")
    List<Usuario> findMotoristasByResponsavelId(@Param("responsavelId") int responsavelId);
}
