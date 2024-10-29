package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.saferide.model.entity.solicitacao.Solicitacao;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {
    List<Solicitacao> findByMotoristaId(int motoristaId);
    List<Solicitacao> findByResponsavelId(int responsavelId);
    Optional<Solicitacao> findByDependenteIdAndStatusIn(int responsavelId, Integer[] status);
}
