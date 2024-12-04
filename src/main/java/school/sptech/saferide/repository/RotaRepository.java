package school.sptech.saferide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.rota.RotaEscolaEndereco;
import school.sptech.saferide.model.entity.rota.RotaListarEnderecos;

import java.util.List;
import java.util.Optional;

public interface RotaRepository extends JpaRepository<Rota, Integer> {
    List<Rota> findByDependenteId(int dependenteId);
    List<Rota> findByTrajetoId(Integer trajetoId);
    Optional<Rota> findByTrajetoIdAndDependenteIdAndEnderecoId(int trajetoId, int dependenteId, int enderecoId);

    @Query(value = """
SELECT e.latitude AS latitude, e.longitude AS longitude
FROM rota r
INNER JOIN trajeto t ON r.trajeto_id = t.id
INNER JOIN endereco e ON r.endereco_id = e.id
WHERE t.id = :idTrajeto
""")
    List<RotaListarEnderecos> listarParadaPorTrajeto(@Param("idTrajeto") int idTrajeto);

    @Query(value = """
SELECT e.latitude AS latitude, e.longitude AS longitude
FROM rota r
INNER JOIN trajeto t ON r.trajeto_id = t.id
INNER JOIN escola es ON t.escola_id = es.id
INNER JOIN endereco e ON es.endereco_id = e.id
WHERE t.id = :idTrajeto
""")
    RotaEscolaEndereco listarEnderecoEscolaPorTrajeto(@Param("idTrajeto") int idTrajeto);

}

