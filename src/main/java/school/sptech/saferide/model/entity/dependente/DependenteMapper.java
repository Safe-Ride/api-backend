package school.sptech.saferide.model.entity.dependente;

import java.util.List;

public class DependenteMapper {
    public static DependenteResponse toDto(Dependente entity){
        if (entity == null) return null;

        DependenteResponse dto = new DependenteResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setSerie(entity.getSerie());
        dto.setResponsavel(DependenteResponse.Usuario.builder()
                .id(entity.getResponsavel().getId())
                .nome(entity.getResponsavel().getNome())
                .build());
        dto.setEscola(DependenteResponse.Escola.builder()
                .id(entity.getEscola().getId())
                .nome(entity.getEscola().getNome())
                .build());
        if (dto.getMotorista() != null) {
            dto.setMotorista(DependenteResponse.Usuario.builder()
                    .id(entity.getMotorista().getId())
                    .nome(entity.getMotorista().getNome())
                    .build());
        }
        return dto;
    }

    public static List<DependenteResponse> toDto(List<Dependente> entities){
        return entities.stream()
                .map(DependenteMapper::toDto)
                .toList();
    }

    public static Dependente toEntity(DependenteRequest dto){
        if (dto == null) return null;

        Dependente entity = new Dependente();
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setSerie(dto.getSerie());
        return entity;
    }
}
