package saferide.sptech.apibackend.dto.mensagem;

import saferide.sptech.apibackend.entity.Historico;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class MensagemMapper {

    public static Mensagem toEntity(MensagemRequest dto, Dependente dependente){
        if (dto == null) return null;

        Mensagem entity = new Mensagem();
        entity.setHistorico(Historico.builder()
                .id(dto.getHistoricoId())
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
        dto.setHistorico(MensagemResponse.Historico.builder()
                .id(entity.getHistorico().getId())
                .build());;
        dto.setUsuario(MensagemResponse.Usuario.builder()
                .id(entity.getUsuario().getId())
                .build());
        dto.setDependente(MensagemResponse.Dependente.builder()
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
