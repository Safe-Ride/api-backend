package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.mensagem.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

}
