package school.sptech.saferide.model.entity.conversa;

import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import school.sptech.saferide.model.entity.mensagem.Mensagem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConversaMapper {
    public static ConversaResponse toDto(Conversa entity) {
        if (entity == null) return null;

        ConversaResponse dto = new ConversaResponse();
        dto.setId(entity.getId());
        dto.setResponsavel(ConversaResponse.Usuario.builder()
                .id(entity.getResponsavel().getId())
                .nome(entity.getResponsavel().getNome())
                .telefone(entity.getResponsavel().getTelefone())
                .build());
        dto.setMotorista(ConversaResponse.Usuario.builder()
                .id(entity.getMotorista().getId())
                .nome(entity.getMotorista().getNome())
                .telefone(entity.getMotorista().getTelefone())
                .build());
        if (entity.getMensagens() != null) {
            dto.setMensagems(entity.getMensagens().stream()
                    .map(s -> ConversaResponse.Mensagem.builder()
                            .id(s.getId())
                            .data(s.getData())
                            .status(s.getStatus())
                            .usuarioId(s.getUsuario().getId())
                            .dependente(ConversaResponse.Mensagem.Dependente.builder()
                                    .id(s.getDependente().getId())
                                    .nome(s.getDependente().getNome())
                                    .build())
                            .build())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static Conversa toEntity(ConversaRequest dto) {
        if (dto == null) return null;
        return new Conversa();
    }

//    public static List<ListarConversasMotorista> toListarConversasMotoristaDto(Tuple tuple) {
//        List<ListarConversasMotorista> lista = new ArrayList<>();
//        for (Tuple t : tuple) {
//            ListarConversasMotorista dto = new ListarConversasMotorista();
//            dto.setId((Integer) t.get("id"));
//            dto.setFoto((String) t.get("foto"));
//            dto.setNome((String) t.get("nome"));
//            dto.setMensagem((Mensagem) t.get("mensagem"));
//            dto.setLocalDateTime((LocalDateTime) t.get("horario"));
//            dto.setQuantidadeMensagens((Integer) t.get("qtdMensagens"));
//
//            lista.add(dto);
//        }
//        return lista;
//    }
}
