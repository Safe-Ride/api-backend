package saferide.sptech.apibackend.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saferide.sptech.apibackend.entity.view.PagamentosTotalEfetuadosView;

@Repository
public interface PagamentosTotalEfetuadosViewRepository extends JpaRepository<PagamentosTotalEfetuadosView, Integer> {

}
