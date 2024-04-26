package saferide.sptech.apibackend.dto.trajeto;

import saferide.sptech.apibackend.entity.Trajeto;

import java.util.List;

public class TrajetoMapper {

    public static TrajetoResponse toDto(Trajeto entity){
        if (entity == null) return null;

        TrajetoResponse dto = new TrajetoResponse();
        dto.setId(entity.getId());
        dto.setEscola(entity.getEscola());
        dto.setTipo(entity.getTipo());
        dto.setDiaSemana(entity.getDiaSemana());
        return dto;
    }

    public static List<TrajetoResponse> toDto(List<Trajeto> entities){
        return entities.stream()
                .map(TrajetoMapper::toDto)
                .toList();
    }

    public static Trajeto toEntity(TrajetoRequest dto){
        if (dto == null) return null;

        Trajeto entity = new Trajeto();
        entity.setEscola(dto.getEscola());
        entity.setTipo(dto.getTipo());
        entity.setDiaSemana(dto.getDiaSemana());
        return entity;
    }

    public static Trajeto toEntityAtt(TrajetoRequestUpdate dto, Trajeto entity){
        if (dto == null) return null;

        if (dto.getEscola() != null) entity.setEscola(dto.getEscola());
        if (dto.getTipo() != null) entity.setTipo(dto.getTipo());
        if (dto.getDiaSemana() != null) entity.setDiaSemana(dto.getDiaSemana());
        return entity;
    }

}
