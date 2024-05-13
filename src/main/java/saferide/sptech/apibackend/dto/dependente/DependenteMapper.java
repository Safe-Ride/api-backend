package saferide.sptech.apibackend.dto.dependente;

import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Dependente;

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

    public static Dependente toEntity(DependenteRequest dto, Usuario responsavel, Escola escola){
        if (dto == null) return null;

        Dependente entity = new Dependente();
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setSerie(dto.getSerie());
        entity.setResponsavel(responsavel);
        entity.setEscola(escola);
        return entity;
    }

    public static Dependente toEntityAtt(DependenteRequestUpdate dto, Dependente entity){
        if (dto == null) return null;

        if (dto.getNome() != null) entity.setNome(dto.getNome());
        if (dto.getNome() != null) entity.setDataNascimento(dto.getDataNascimento());
        if (dto.getNome() != null) entity.setSerie(dto.getSerie());
        return entity;
    }

}
