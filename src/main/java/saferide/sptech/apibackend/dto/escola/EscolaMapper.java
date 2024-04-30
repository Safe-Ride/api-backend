package saferide.sptech.apibackend.dto.escola;

import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Transporte;

import java.util.List;

public class EscolaMapper {

    public static EscolaResponse toDto(Escola entity){
        if (entity == null) return null;

        EscolaResponse dto = new EscolaResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(toEnderecoDto(entity.getEndereco()));
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

    public static EscolaEnderecoResponse toEnderecoDto(Endereco entity){
        if (entity == null) return null;

        EscolaEnderecoResponse dto = new EscolaEnderecoResponse();
        dto.setId(entity.getId());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setCep(entity.getCep());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        return dto;
    }

    public static List<EscolaEnderecoResponse> toEnderecoDto(List<Endereco> entities){
        return entities.stream()
                .map(EscolaMapper::toEnderecoDto)
                .toList();
    }

    public static EscolaTransporteResponse toTransporteDto(Transporte entity){
        if (entity == null) return null;

        EscolaTransporteResponse dto = new EscolaTransporteResponse();
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        return dto;
    }

    public static List<EscolaTransporteResponse> toTransporteDto(List<Transporte> entities){
        return entities.stream()
                .map(EscolaMapper::toTransporteDto)
                .toList();
    }

}
