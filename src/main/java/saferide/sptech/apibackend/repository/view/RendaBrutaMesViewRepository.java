package saferide.sptech.apibackend.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saferide.sptech.apibackend.entity.view.RendaBrutaMesView;

@Repository
public interface RendaBrutaMesViewRepository extends JpaRepository<RendaBrutaMesView, Integer> {

}
