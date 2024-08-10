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
}
