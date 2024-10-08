package school.sptech.saferide.repository;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.conversa.ListarConversasMotorista;

import java.util.List;
import java.util.Optional;

public interface ConversaRepository extends JpaRepository<Conversa, Integer> {
    Optional<Conversa> findByResponsavelIdAndMotoristaId(int responsavelId, int motoristaId);

    @Query(value = """
    select new school.sptech.saferide.model.entity.conversa.ListarConversasMotorista(
        mt.id, mt.imagem.caminho, mt.nome, m.status, m.data, count(m.status))
    from Usuario mt
    join Conversa c on mt = c.motorista
    join Mensagem m on c = m.conversa
    where c.responsavel.id = :responsavelId
      and m.data = (
          select max(m2.data)
          from Mensagem m2
          where m2.conversa = c
      )
    group by mt.id, mt.imagem.caminho, mt.nome, m.status, m.data
""")
    List<ListarConversasMotorista> findByResponsavelId(@Param("responsavelId") int responsavelId);
}
