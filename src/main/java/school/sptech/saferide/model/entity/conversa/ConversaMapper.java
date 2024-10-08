package school.sptech.saferide.model.entity.conversa;

import java.util.stream.Collectors;

public class ConversaMapper {
    public static ConversaResponse toDto(Conversa entity) {
        if (entity == null) return null;

        ConversaResponse dto = new ConversaResponse();
        dto.setId(entity.getId());
        dto.setResponsavel(ConversaResponse.Usuario.builder()
                .id(entity.getResponsavel().getId())
                .nome(entity.getResponsavel().getNome())
                .build());
        dto.setMotorista(ConversaResponse.Usuario.builder()
                .id(entity.getMotorista().getId())
                .nome(entity.getMotorista().getNome())
                .build());
        if (entity.getMensagens() != null) {
            dto.setMensagens(entity.getMensagens().stream()
                    .map(s -> ConversaResponse.Mensagem.builder()
                            .id(s.getId())
                            .horario(s.getData())
                            .status(s.getStatus().exibicao)
                            .tipoUsuario(s.getUsuario().getTipo())
                            .nome(s.getDependente().getNome())
                            .lida(s.getLida())
                            .build())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static Conversa toEntity(ConversaRequest dto) {
        if (dto == null) return null;
        return new Conversa();
    }
}
