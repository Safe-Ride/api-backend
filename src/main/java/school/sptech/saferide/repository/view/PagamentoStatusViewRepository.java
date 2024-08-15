package school.sptech.saferide.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.view.PagamentoStatusView;

@Repository
public interface PagamentoStatusViewRepository extends JpaRepository<PagamentoStatusView, Integer> {

}
