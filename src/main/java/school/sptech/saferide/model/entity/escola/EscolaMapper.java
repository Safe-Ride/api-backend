package school.sptech.saferide.model.entity.escola;

import java.util.List;

public class EscolaMapper {
    public static EscolaResponse toDto(Escola entity){
        if (entity == null) return null;

        EscolaResponse dto = new EscolaResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(EscolaResponse.Endereco.builder()
                .id(entity.getEndereco().getId())
                .cep(entity.getEndereco().getCep())
                .build());
        return dto;
    }

    public static List<EscolaResponse> toDto(List<Escola> entities){
        return entities.stream()
                .map(EscolaMapper::toDto)
                .toList();
    }

    public static Escola toEntity(EscolaRequest dto){
        if (dto == null) return null;

        Escola entity = new Escola();
        entity.setNome(dto.getNome());
        return entity;
    }
}
