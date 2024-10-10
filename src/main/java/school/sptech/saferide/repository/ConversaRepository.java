package school.sptech.saferide.repository;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.saferide.model.entity.conversa.Conversa;
import school.sptech.saferide.model.entity.conversa.ListarConversasMotorista;
import school.sptech.saferide.model.entity.conversa.ListarConversasResponsavel;
import school.sptech.saferide.model.enums.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface ConversaRepository extends JpaRepository<Conversa, Integer> {
    @Query(value = """
            select c
            from Conversa as c
            where c.responsavel.id = :responsavelId
            and c.motorista.id = :motoristaId
            """)
    Optional<Conversa> findByResponsavelIdAndMotoristaId(@Param("responsavelId") int responsavelId, @Param("motoristaId") int motoristaId);

    @Query(value = """
                select new school.sptech.saferide.model.entity.conversa.ListarConversasMotorista(
                    mt.id, c.id, mt.imagem.caminho, mt.nome,
                    (select m3.status from Mensagem m3 where m3.conversa = c order by m3.data desc limit 1),
                    (select max(m2.data) from Mensagem m2 where m2.conversa = c),
                    (select count(m4.id) from Mensagem m4
                     join Usuario u on m4.usuario = u
                     where m4.conversa = c and m4.lida = false and u.tipo = school.sptech.saferide.model.enums.TipoUsuario.MOTORISTA)
                )
                from Usuario mt
                join Conversa c on mt = c.motorista
                join Mensagem m on c.id = m.conversa.id
                where c.responsavel.id = :responsavelId
                group by mt.id, mt.imagem.caminho, mt.nome
            """)
    List<ListarConversasMotorista> findByResponsavelId(@Param("responsavelId") int responsavelId);

    @Query(value = """
            select new school.sptech.saferide.model.entity.conversa.ListarConversasResponsavel(
                    rp.id, c.id, rp.imagem.caminho, rp.nome,
                    (select m3.status from Mensagem m3 where m3.conversa = c order by m3.data desc limit 1),
                    (select max(m2.data) from Mensagem m2 where m2.conversa = c),
                    (select count(m4.id) from Mensagem m4
                     join Usuario u on m4.usuario = u
                     where m4.conversa = c and m4.lida = false and u.tipo = school.sptech.saferide.model.enums.TipoUsuario.RESPONSAVEL)
                )
                from Usuario rp
                join Conversa c on rp = c.responsavel
                join Mensagem m on c.id = m.conversa.id
                where c.motorista.id = :motoristaId
                group by rp.id, rp.imagem.caminho, rp.nome
            """)
    List<ListarConversasResponsavel> findByMotoristaId(@Param("motoristaId") int motoristaId);
}
