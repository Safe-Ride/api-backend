package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import saferide.sptech.apibackend.entity.Pagamento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    @Query("SELECT p.dataCriacao FROM Pagamento p WHERE p.pagador.id = :pagadorId ORDER BY p.dataCriacao DESC LIMIT 1")
    Optional<LocalDate> findLastDataCriacaoByPagadorId(@Param("pagadorId") int pagadorId);

//    @Query("SELECT p.dataCriacao FROM Pagamento p WHERE p.pagador.id = :pagadorId ORDER BY p.dataCriacao DESC LIMIT 1")
//    List<Pagamento> findByMotorista(int motoristaId);
//
//    @Query("SELECT p.dataCriacao FROM Pagamento p WHERE p.pagador.id = :pagadorId ORDER BY p.dataCriacao DESC LIMIT 1")
//    List<Pagamento> findByResponsavel(int responsavelId);

}
