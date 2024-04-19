package saferide.sptech.apibackend.dto.dependente;

import saferide.sptech.apibackend.dto.cliente.ClienteRequestUpdateDto;
import saferide.sptech.apibackend.dto.cliente.ClienteResponseDto;
import saferide.sptech.apibackend.entity.Cliente;
import saferide.sptech.apibackend.entity.Dependente;

import java.util.List;

public class DependenteMapper {
    public static DependenteResponse toDto(Dependente entity){
        if (entity == null) return null;

        DependenteResponse dto = new DependenteResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setEscola(entity.getEscola());
        dto.setSerie(entity.getSerie());
        dto.setCliente(entity.getCliente());
        return dto;

    }

    public static Dependente toEntity(DependenteRequest dto, Cliente cliente){
        if (dto == null) return null;

        Dependente entity = new Dependente();
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setEscola(dto.getEscola());
        entity.setSerie(dto.getSerie());
        entity.setCliente(cliente);
        return entity;
    }

    public static Dependente toEntityAtt(DependenteRequestUpdate dto, Dependente entity){
        if (dto == null) return null;

        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setEscola(dto.getEscola());
        entity.setSerie(dto.getSerie());
        return entity;
    }

    public static List<DependenteResponse> toDto(List<Dependente> entities){
        return entities.stream()
                .map(DependenteMapper::toDto)
                .toList();
    }
}
