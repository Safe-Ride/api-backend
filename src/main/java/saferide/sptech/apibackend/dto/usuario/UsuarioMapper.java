package saferide.sptech.apibackend.dto.usuario;

import saferide.sptech.apibackend.entity.Usuario;

import java.util.List;

public class UsuarioMapper {
    public static UsuarioResponse toDto(Usuario entity){
        if (entity == null) return null;

        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setTipo(entity.getTipo());
        return dto;

    }

    public static Usuario toEntity(UsuarioRequest dto){
        if (dto == null) return null;

        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setCpf(dto.getCpf());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setTipo(dto.getTipo());
        return entity;
    }

    public static Usuario toEntityAtt(UsuarioRequestUpdate dto, Usuario entity){
        if (dto == null) return null;

        if (dto.getNome() != null) entity.setNome(dto.getNome());
        if (dto.getEmail() != null)entity.setEmail(dto.getEmail());
        if (dto.getCpf() != null)entity.setCpf(dto.getCpf());
        if (dto.getTelefone() != null)entity.setTelefone(dto.getTelefone());
        if (dto.getDataNascimento() != null)entity.setDataNascimento(dto.getDataNascimento());
        return entity;
    }

    public static List<UsuarioResponse> toDto(List<Usuario> entities){
        return entities.stream()
                .map(UsuarioMapper::toDto)
                .toList();
    }
}
