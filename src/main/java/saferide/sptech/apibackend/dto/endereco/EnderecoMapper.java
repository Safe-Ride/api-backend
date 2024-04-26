package saferide.sptech.apibackend.dto.endereco;

import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Endereco;

import java.util.List;

public class EnderecoMapper {

    public static EnderecoResponse toDto(Endereco entity){
        if (entity == null) return null;

        EnderecoResponse dto = new EnderecoResponse();
        dto.setId(entity.getId());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setCep(entity.getCep());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setUsuario(toUsuarioDto(entity.getUsuario()));
        return dto;
    }

    public static List<EnderecoResponse> toDto(List<Endereco> entities){
        return entities.stream()
                .map(EnderecoMapper::toDto)
                .toList();
    }

    public static Endereco toEntity(EnderecoRequest dto, Usuario usuario){
        if (dto == null) return null;

        Endereco entity = new Endereco();
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setCep(dto.getCep());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setUsuario(usuario);
        return entity;
    }

    public static Endereco toEntityAtt(EnderecoRequestUpdate dto, Endereco entity){
        if (dto == null) return null;

        if (dto.getLatitude() != null) entity.setLatitude(dto.getLatitude());
        if (dto.getLongitude() != null) entity.setLongitude(dto.getLongitude());
        if (dto.getCep() != null) entity.setCep(dto.getCep());
        if (dto.getNumero() != null) entity.setNumero(dto.getNumero());
        if (dto.getComplemento() != null) entity.setComplemento(dto.getComplemento());
        return entity;
    }

    public static EnderecoUsuarioResponse toUsuarioDto(Usuario entity){
        if (entity == null) return null;

        EnderecoUsuarioResponse dto = new EnderecoUsuarioResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }

    public static List<EnderecoUsuarioResponse> toUsuarioDto(List<Usuario> entities){
        return entities.stream()
                .map(EnderecoMapper::toUsuarioDto)
                .toList();
    }

}
