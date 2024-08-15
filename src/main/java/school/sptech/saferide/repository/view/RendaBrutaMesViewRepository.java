package school.sptech.saferide.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.view.RendaBrutaMesView;

@Repository
public interface RendaBrutaMesViewRepository extends JpaRepository<RendaBrutaMesView, Integer> {

}
