package saferide.sptech.apibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saferide.sptech.apibackend.entity.Historico;

import java.util.List;
import java.util.Optional;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {

    Optional<Historico> findByResponsavelIdAndMotoristaId(int responsavelId, int motoristaId);
    List<Historico> findByResponsavelId(int responsavelId);
    List<Historico> findByMotoristaId(int motoristaId);
}
