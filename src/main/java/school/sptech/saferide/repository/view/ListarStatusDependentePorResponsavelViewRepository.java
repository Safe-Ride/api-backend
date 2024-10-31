package school.sptech.saferide.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.view.ListarStatusDependentePorResponsavelView;

import java.util.List;

@Repository
public interface ListarStatusDependentePorResponsavelViewRepository extends JpaRepository<ListarStatusDependentePorResponsavelView, Integer> {
  @Query("SELECT lsdprv FROM ListarStatusDependentePorResponsavelView lsdprv WHERE lsdprv.responsavelId = :responsavelId")
   List<ListarStatusDependentePorResponsavelView> findByResponsavelId(@Param("responsavelId") int responsavelId);
}
