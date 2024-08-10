package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.conversa.Conversa;

import java.util.Optional;

public interface ConversaRepository extends JpaRepository<Conversa, Integer> {
    Optional<Conversa> findByResponsavelIdAndMotoristaId(int responsavelId, int motoristaId);
}
