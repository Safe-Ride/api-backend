package school.sptech.saferide.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String username);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario u SET u.nome = :alteracao WHERE u.id = :id")
    void atualizarNome(@Param("id") Integer id, @Param("alteracao") Object alteracao);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario u SET u.email = :alteracao WHERE u.id = :id")
    void atualizarEmail(@Param("id") Integer id, @Param("alteracao") Object alteracao);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario u SET u.cpf = :alteracao WHERE u.id = :id")
    void atualizarCpf(@Param("id") Integer id, @Param("alteracao") Object alteracao);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario u SET u.telefone = :alteracao WHERE u.id = :id")
    void atualizarTelefone(@Param("id") Integer id, @Param("alteracao") Object alteracao);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuario u SET u.dataNascimento = :alteracao WHERE u.id = :id")
    void atualizarDataNascimento(@Param("id") Integer id, @Param("alteracao") Object alteracao);
}
