package saferide.sptech.apibackend.dto.chat;

import saferide.sptech.apibackend.entity.Chat;
import saferide.sptech.apibackend.entity.Usuario;

import java.util.stream.Collectors;

public class ChatMapper {

    public static ChatResponse toDto(Chat entity) {
        if (entity == null) return null;

        ChatResponse dto = new ChatResponse();
        dto.setId(entity.getId());
        dto.setResponsavel(ChatResponse.Usuario.builder()
                .id(entity.getResponsavel().getId())
                .nome(entity.getResponsavel().getNome())
                .telefone(entity.getResponsavel().getTelefone())
                .build());
        dto.setMotorista(ChatResponse.Usuario.builder()
                .id(entity.getMotorista().getId())
                .nome(entity.getMotorista().getNome())
                .telefone(entity.getMotorista().getTelefone())
                .build());
        if (entity.getMensagens() != null) {
            dto.setMensagems(entity.getMensagens().stream()
                    .map(s -> ChatResponse.Mensagem.builder()
                            .id(s.getId())
                            .data(s.getData())
                            .status(s.getStatus())
                            .usuarioId(s.getUsuario().getId())
                            .dependente(ChatResponse.Mensagem.Dependente.builder()
                                    .id(s.getDependente().getId())
                                    .nome(s.getDependente().getNome())
                                    .build())
                            .build())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static Chat toEntity(ChatRequest dto) {
        if (dto == null) return null;

        Chat entity = new Chat();
        entity.setResponsavel(Usuario.builder()
                .id(dto.getResponsavelId())
                .build());
        entity.setMotorista(Usuario.builder()
                .id(dto.getMotoristaId())
                .build());
        return entity;
    }

}
