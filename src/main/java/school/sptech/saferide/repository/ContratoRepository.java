package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.saferide.model.entity.contrato.Contrato;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    List<Contrato> findByMotoristaId(int motoristaId);
    List<Contrato> findByResponsavelId(int responsavelId);

    List<Contrato> findByMotoristaIdAndDataBetween(int id, LocalDate inicio, LocalDate fim);
}
