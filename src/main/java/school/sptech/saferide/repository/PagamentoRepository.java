package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.pagamento.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
