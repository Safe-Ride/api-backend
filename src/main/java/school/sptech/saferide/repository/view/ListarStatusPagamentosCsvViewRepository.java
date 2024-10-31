package school.sptech.saferide.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.view.ListarStatusPagamentosCsvView;

import java.util.List;

@Repository
public interface ListarStatusPagamentosCsvViewRepository extends JpaRepository<ListarStatusPagamentosCsvView, Integer> {
    List<ListarStatusPagamentosCsvView> findByMotoristaId(int motoristaId);
}
