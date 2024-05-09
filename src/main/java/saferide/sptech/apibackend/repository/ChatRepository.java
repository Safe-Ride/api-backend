package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Chat;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    Optional<Chat> findByResponsavelIdAndMotoristaId(int responsavelId, int motoristaId);
}
