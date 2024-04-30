package saferide.sptech.apibackend.dto.trajeto;

import saferide.sptech.apibackend.dto.endereco.EnderecoMapper;
import saferide.sptech.apibackend.dto.escola.EscolaMapper;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Escola;
import saferide.sptech.apibackend.entity.Trajeto;

import java.util.List;

public class TrajetoMapper {

    public static TrajetoResponse toDto(Trajeto entity){
        if (entity == null) return null;

        TrajetoResponse dto = new TrajetoResponse();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setDiaSemana(entity.getDiaSemana());
        dto.setEscola(toEscolaDto(entity.getEscola()));
        return dto;
    }

    public static List<TrajetoResponse> toDto(List<Trajeto> entities){
        return entities.stream()
                .map(TrajetoMapper::toDto)
                .toList();
    }

    public static Trajeto toEntity(TrajetoRequest dto, Escola escola){
        if (dto == null) return null;

        Trajeto entity = new Trajeto();
        entity.setTipo(dto.getTipo());
        entity.setDiaSemana(dto.getDiaSemana());
        entity.setEscola(escola);
        return entity;
    }

    public static Trajeto toEntityAtt(TrajetoRequestUpdate dto, Trajeto entity){
        if (dto == null) return null;

        if (dto.getTipo() != null) entity.setTipo(dto.getTipo());
        if (dto.getDiaSemana() != null) entity.setDiaSemana(dto.getDiaSemana());
        return entity;
    }

    public static TrajetoEscolaResponse toEscolaDto(Escola entity){
        if (entity == null) return null;

        TrajetoEscolaResponse dto = new TrajetoEscolaResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(EscolaMapper.toEnderecoDto(entity.getEndereco()));
        return dto;
    }

    public static List<TrajetoEscolaResponse> toEscolaDto(List<Escola> entities){
        return entities.stream()
                .map(TrajetoMapper::toEscolaDto)
                .toList();
    }
}
