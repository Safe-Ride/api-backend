package saferide.sptech.apibackend.dto.chat;

import saferide.sptech.apibackend.entity.Chat;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Usuario;

import java.util.List;

public class ChatMapper {

    public static ChatResponse toDto(Chat entity) {
        if (entity == null) return null;

        ChatResponse dto = new ChatResponse();
        dto.setId(entity.getId());
        dto.setResponsavel(toUsuarioDto(entity.getResponsavel()));
        dto.setMotorista(toUsuarioDto(entity.getMotorista()));
        if (entity.getMensagens() != null) dto.setMensagems(toMensagemDto(entity.getMensagens()));
        return dto;
    }

    public static Chat toEntity(ChatRequest dto) {
        if (dto == null) return null;

        Chat entity = new Chat();
        entity.setResponsavel(Usuario.builder().id(dto.getResponsavelId()).build());
        entity.setMotorista(Usuario.builder().id(dto.getMotoristaId()).build());
        return entity;
    }

    public static ChatMensagemResponse toMensagemDto(Mensagem entity) {
        if (entity == null) return null;

        ChatMensagemResponse dto = new ChatMensagemResponse();
        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setStatus(entity.getStatus());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setDependente(ChatDependenteResponse.builder()
                .id(entity.getDependente().getId())
                .nome(entity.getDependente().getNome())
                .build());
        return dto;
    }

    public static List<ChatMensagemResponse> toMensagemDto(List<Mensagem> entities) {
        return entities.stream()
                .map(ChatMapper::toMensagemDto)
                .toList();
    }

    public static ChatUsuarioResponse toUsuarioDto(Usuario entity) {
        if (entity == null) return null;

        ChatUsuarioResponse dto = new ChatUsuarioResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setTelefone(entity.getTelefone());
        return dto;
    }

}
