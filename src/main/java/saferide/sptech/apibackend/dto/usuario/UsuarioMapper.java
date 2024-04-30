package saferide.sptech.apibackend.dto.usuario;

import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.service.autentication.UsuarioTokenDto;

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
        if (entity.getDependentes() != null) {
            dto.setDependentes(toDependenteDto(entity.getDependentes()));
        }
        if (entity.getEnderecos() != null) {
            dto.setEnderecos(toEnderecoDto(entity.getEnderecos()));
        }
        return dto;
    }

    public static List<UsuarioResponse> toDto(List<Usuario> entities){
        return entities.stream()
                .map(UsuarioMapper::toDto)
                .toList();
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

    public static UsuarioDependenteResponse toDependenteDto(Dependente entity){
        if (entity == null) return null;

        UsuarioDependenteResponse dto = new UsuarioDependenteResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setSerie(entity.getSerie());
        return dto;
    }

    public static List<UsuarioDependenteResponse> toDependenteDto(List<Dependente> entities){
        return entities.stream()
                .map(UsuarioMapper::toDependenteDto)
                .toList();
    }

    public static UsuarioEnderecoResponse toEnderecoDto(Endereco entity){
        if (entity == null) return null;

        UsuarioEnderecoResponse dto = new UsuarioEnderecoResponse();
        dto.setId(entity.getId());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setCep(entity.getCep());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        return dto;
    }

    public static List<UsuarioEnderecoResponse> toEnderecoDto(List<Endereco> entities){
        return entities.stream()
                .map(UsuarioMapper::toEnderecoDto)
                .toList();
    }

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }



}
