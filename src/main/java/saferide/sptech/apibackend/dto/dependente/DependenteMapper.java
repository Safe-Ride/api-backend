package saferide.sptech.apibackend.dto.dependente;

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
        dto.setEscola(entity.getEscola());
        dto.setSerie(entity.getSerie());
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));
        return dto;

    }

    public static List<DependenteResponse> toDto(List<Dependente> entities){
        return entities.stream()
                .map(DependenteMapper::toDto)
                .toList();
    }

    public static Dependente toEntity(DependenteRequest dto, Usuario usuario){
        if (dto == null) return null;

        Dependente entity = new Dependente();
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setEscola(dto.getEscola());
        entity.setSerie(dto.getSerie());
        entity.setUsuario(usuario);
        return entity;
    }

    public static Dependente toEntityAtt(DependenteRequestUpdate dto, Dependente entity){
        if (dto == null) return null;

        if (dto.getNome() != null) entity.setNome(dto.getNome());
        if (dto.getNome() != null) entity.setDataNascimento(dto.getDataNascimento());
        if (dto.getNome() != null) entity.setEscola(dto.getEscola());
        if (dto.getNome() != null) entity.setSerie(dto.getSerie());
        return entity;
    }

    public static DependenteUsuarioResponse toUsuarioDto(Usuario entity) {
        if (entity == null) return null;

        DependenteUsuarioResponse dto = new DependenteUsuarioResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setTipo(entity.getTipo());
        return dto;
    }

    public static List<DependenteUsuarioResponse> toUsuarioDto(List<Usuario> entities) {
        return entities.stream()
                .map(DependenteMapper::toUsuarioDto)
                .toList();
    }

}
