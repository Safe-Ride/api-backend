package saferide.sptech.apibackend.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saferide.sptech.apibackend.entity.view.PagamentoStatusView;

@Repository
public interface PagamentoStatusViewRepository extends JpaRepository<PagamentoStatusView, Integer> {

}
