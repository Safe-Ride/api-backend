package school.sptech.saferide.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.view.ListarStatusDependentePorResponsavelView;

import java.util.List;

@Repository
public interface ListarStatusDependentePorResponsavelViewRepository extends JpaRepository<ListarStatusDependentePorResponsavelView, Integer> {
    List<ListarStatusDependentePorResponsavelView> findByResponsavelId(int responsavelId);
}
