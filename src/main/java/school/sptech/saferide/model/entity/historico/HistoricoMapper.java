package school.sptech.saferide.model.entity.historico;

import java.util.List;

public class HistoricoMapper {

    public static HistoricoResponse toDto(Historico entity) {
        if (entity == null) return null;

        HistoricoResponse dto = new HistoricoResponse();
        dto.setId(entity.getId());
        if (entity.getTrajeto() != null) {
            dto.setTrajeto(HistoricoResponse.Trajeto.builder()
                    .id(entity.getTrajeto().getId())
                    .build());
        }
        dto.setHorarioInicio(entity.getHorarioInicio());
        dto.setHorarioFim(entity.getHorarioFim());
        return dto;
    }

    public static List<HistoricoResponse> toDto(List<Historico> entities) {
        return entities.stream()
                .map(HistoricoMapper::toDto)
                .toList();
    }

    public static Historico toEntity(HistoricoRequest dto){
        if (dto == null) return null;

        Historico entity = new Historico();
        entity.setHorarioInicio(dto.getHorarioInicio());
        entity.setHorarioFim(dto.getHorarioFim());
        return entity;
    }
}
