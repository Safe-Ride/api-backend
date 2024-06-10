package saferide.sptech.apibackend.dto.trajeto;

import saferide.sptech.apibackend.entity.Trajeto;

import java.util.List;

public class TrajetoMapper {

    public static TrajetoResponse toDto(Trajeto entity){
        if (entity == null) return null;

        TrajetoResponse dto = new TrajetoResponse();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setDiaSemana(entity.getDiaSemana());
        dto.setEscola(TrajetoResponse.Escola.builder()
                .id(entity.getEscola().getId())
                .nome(entity.getEscola().getNome())
                .endereco(TrajetoResponse.Escola.Endereco.builder()
                        .id(entity.getEscola().getEndereco().getId())
                        .cep(entity.getEscola().getEndereco().getCep())
                        .build())
                .build());
        dto.setMotorista(TrajetoResponse.Motorista.builder()
                .id(entity.getMotorista().getId())
                .nome(entity.getMotorista().getNome())
                .build());
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
        entity.setTipo(dto.getTipo());
        entity.setDiaSemana(dto.getDiaSemana());
        return entity;
    }

    public static Trajeto toEntityAtt(TrajetoRequestUpdate dto, Trajeto entity){
        if (dto == null) return null;

        if (dto.getTipo() != null) entity.setTipo(dto.getTipo());
        if (dto.getDiaSemana() != null) entity.setDiaSemana(dto.getDiaSemana());
        return entity;
    }

}
