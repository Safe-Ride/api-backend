package school.sptech.saferide.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.usuario.MotoristaPerfilResponse;
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

    @Query("SELECT DISTINCT u FROM Usuario u " +
            "JOIN Trajeto t ON t.motorista = u " +
            "WHERE u.tipo = 0 AND t.escola.id = " +
            "(SELECT d.escola.id FROM Dependente d WHERE d.id = :dependenteId)")
    List<Usuario> findMotoristaByEscolaId(@Param("dependenteId") int dependenteId);
    @Query("SELECT new school.sptech.saferide.model.entity.usuario.MotoristaPerfilResponse(" +
            "u.id, u.nome, u.telefone, u.email, u.dataNascimento, u.imagem, t.id, t.placa, t.cnpj) " +
            "FROM Usuario u " +
            "JOIN Transporte t ON t.usuario.id = u.id " +
            "WHERE u.id = :id")
    Optional<MotoristaPerfilResponse> findMotoristaTranporteById(@Param("id") int dependenteId);
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
    @Query("SELECT DISTINCT m.id AS id, m.nome AS nome, m.imagem.caminho AS foto " +
            "FROM Dependente  d " +
            "JOIN Usuario r ON d.responsavel.id = r.id " +
            "JOIN Usuario m ON d.motorista.id = m.id " +
            "JOIN Escola e ON d.escola.id = e.id " +
            "WHERE r.id = :responsavelId")
    List<Object[]> findMotoristasDisponiveisByResponsavelId(@Param("responsavelId") int responsavelId);

//    @Query("SELECT DISTINCT m.id AS id, m.nome AS nome, m.imagem.caminho " +
//            "FROM Dependente d JOIN d.motorista m " +
//            "WHERE d.responsavel.id = :responsavelId")
//    List<Object[]> findMotoristasByResponsavelId(@Param("responsavelId") int motoristaId);
}
