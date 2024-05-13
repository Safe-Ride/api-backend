package saferide.sptech.apibackend.dto.escola;

import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Escola;

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
//        dto.setTrasportes(toTransporteDto(entity.getTransportes()));
        return dto;
    }

    public static List<EscolaResponse> toDto(List<Escola> entities){
        return entities.stream()
                .map(EscolaMapper::toDto)
                .toList();
    }

    public static Escola toEntity(EscolaRequest dto, Endereco endereco){
        if (dto == null) return null;

        Escola entity = new Escola();
        entity.setNome(dto.getNome());
        entity.setEndereco(endereco);
        return entity;
    }

    public static Escola toEntityAtt(EscolaRequestUpdate dto, Escola entity){
        if (dto == null) return null;

        if (dto.getNome() != null) entity.setNome(dto.getNome());
        return entity;
    }

}
