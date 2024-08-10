package school.sptech.saferide.model.entity.transporte;

import java.util.List;

public class TransporteMapper {

    public static TransporteResponse toDto(Transporte entity){
        if (entity == null) return null;

        TransporteResponse dto = new TransporteResponse();
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setCnpj(entity.getCnpj());
        dto.setCnh(entity.getCnh());
        dto.setCrm(entity.getCrm());
        dto.setCrmc(entity.getCrmc());
        dto.setUsuario(TransporteResponse.Usuario.builder()
                .id(entity.getUsuario().getId())
                .nome(entity.getUsuario().getNome())
                .build());
        return dto;
    }

    public static List<TransporteResponse> toDto(List<Transporte> entities){
        return entities.stream()
                .map(TransporteMapper::toDto)
                .toList();
    }

    public static Transporte toEntity(TransporteRequest dto){
        if (dto == null) return null;

        Transporte entity = new Transporte();
        entity.setPlaca(dto.getPlaca());
        entity.setCnpj(dto.getCnpj());
        entity.setCnh(dto.getCnh());
        entity.setCrm(dto.getCrm());
        entity.setCrmc(dto.getCrmc());
        return entity;
    }
}
