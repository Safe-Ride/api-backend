package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Mensagem;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    List<Mensagem> findByChatId(int chatId);

}
