package saferide.sptech.apibackend.dto.mensagem;

import saferide.sptech.apibackend.entity.Chat;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class MensagemMapper {

    public static Mensagem toEntity(MensagemRequest dto, Dependente dependente){
        if (dto == null) return null;

        Mensagem entity = new Mensagem();
        entity.setChat(Chat.builder()
                .id(dto.getChatId())
                .build());
        entity.setUsuario(Usuario.builder()
                .id(dto.getUsuarioId())
                .build());
        entity.setDependente(dependente);
        entity.setStatus(dto.getStatus());
        entity.setData(LocalDateTime.now());
        return entity;
    }

    public static MensagemResponse toDto(Mensagem entity){
        if (entity == null) return null;

        MensagemResponse dto = new MensagemResponse();
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setData(entity.getData());
        dto.setChat(MensagemChatResponse.builder()
                .id(entity.getChat().getId())
                .build());;
        dto.setUsuario(MensagemUsuarioResponse.builder()
                .id(entity.getUsuario().getId())
                .build());
        dto.setDependente(MensagemDependenteResponse.builder()
                .id(entity.getDependente().getId())
                .nome(entity.getDependente().getNome())
                .build());
        return dto;
    }

    public static List<MensagemResponse> toDto(List<Mensagem> entities){
        return entities.stream()
                .map(MensagemMapper::toDto)
                .toList();
    }

}
