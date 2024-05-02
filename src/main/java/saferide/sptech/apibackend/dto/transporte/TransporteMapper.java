package saferide.sptech.apibackend.dto.transporte;

import saferide.sptech.apibackend.entity.Transporte;
import saferide.sptech.apibackend.entity.Usuario;

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
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));
        return dto;
    }

    public static List<TransporteResponse> toDto(List<Transporte> entities){
        return entities.stream()
                .map(TransporteMapper::toDto)
                .toList();
    }

    public static Transporte toEntity(TransporteRequest dto, Usuario usuario){
        if (dto == null) return null;

        Transporte entity = new Transporte();
        entity.setPlaca(dto.getPlaca());
        entity.setCnpj(dto.getCnpj());
        entity.setCnh(dto.getCnh());
        entity.setCrm(dto.getCrm());
        entity.setCrmc(dto.getCrmc());
        entity.setUsuario(usuario);
        return entity;
    }

    public static Transporte toEntityAtt(TransporteRequestUpdate dto, Transporte entity){
        if (dto == null) return null;

        if (dto.getPlaca() != null) entity.setPlaca(dto.getPlaca());
        if (dto.getCnpj() != null) entity.setCnpj(dto.getCnpj());
        if (dto.getCnh() != null) entity.setCnh(dto.getCnh());
        if (dto.getCrm() != null) entity.setCrm(dto.getCrm());
        if (dto.getCrmc() != null) entity.setCrmc(dto.getCrmc());
        return entity;
    }

    public static TransporteUsuarioResponse toUsuarioDto(Usuario entity) {
        if (entity == null) return null;

        TransporteUsuarioResponse dto = new TransporteUsuarioResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }

}
