package school.sptech.saferide.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.dependente.DependentePerfilResponse;
import school.sptech.saferide.model.entity.escola.Escola;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DependenteRepository extends JpaRepository<Dependente, Integer> {
    List<Dependente> findByResponsavelId(int id);
    Optional<Dependente> findByIdAndMotoristaId(int id, int motoristaId);

    @Query("SELECT new school.sptech.saferide.model.entity.dependente.DependentePerfilResponse(" +
            "d.id, d.nome, d.dataNascimento, d.serie, e.nome, u.id, u.nome, u.telefone, t.placa, i.caminho) " +
            "FROM Dependente d " +
            "JOIN d.escola e " +
            "JOIN d.imagem i " +
            "JOIN d.motorista u " +
            "JOIN Transporte t ON t.usuario = u " +
            "WHERE d.id = :id")
    DependentePerfilResponse listarPerfilPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Dependente d SET d.nome = :alteracao WHERE d.id = :id")
    void atualizarNome(@Param("id") Integer id, @Param("alteracao") String alteracao);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Dependente d SET d.dataNascimento = :alteracao WHERE d.id = :id")
    void atualizarDataNascimento(@Param("id") Integer id, @Param("alteracao") LocalDate alteracao);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Dependente d SET d.serie = :alteracao WHERE d.id = :id")
    void atualizarSerie(@Param("id") Integer id, @Param("alteracao") String alteracao);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Dependente d SET d.escola = :alteracao WHERE d.id = :id")
    void atualizarEscola(@Param("id") Integer id, @Param("alteracao") Escola alteracao);
}
