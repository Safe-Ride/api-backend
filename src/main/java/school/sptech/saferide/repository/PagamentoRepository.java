package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.pagamento.Pagamento;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    @Query("SELECT p FROM Pagamento p WHERE p.contrato.id = :contratoId AND YEAR(p.dataVencimento) = :ano")
    List<Pagamento> findByContratoIdAndAno(@Param("contratoId") Integer contratoId, @Param("ano") Integer ano);
}
