package school.sptech.saferide.model.entity.rota;

public class RotaMapper {
    public static Rota toEntity(RotaRequest request) {
        if (request == null) return null;
        return new Rota();
    }

    public static RotaResponse toDto(Rota entity) {
        if (entity == null) return null;

        RotaResponse dto = new RotaResponse();
        dto.setId(entity.getId());
        dto.setDependente(RotaResponse.Dependente.builder()
                        .id(entity.getDependente().getId())
                        .nome(entity.getDependente().getNome())
                        .build());
        dto.setTrajeto(RotaResponse.Trajeto.builder()
                .id(entity.getDependente().getId())
                .build());
        dto.setStatus(entity.getStatus());
        dto.setHorario(entity.getHorario());
        return dto;
    }
}
