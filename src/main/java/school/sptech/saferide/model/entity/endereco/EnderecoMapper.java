package school.sptech.saferide.model.entity.endereco;

import java.util.List;

public class EnderecoMapper {
    public static EnderecoResponse toDto(Endereco entity){
        if (entity == null) return null;

        EnderecoResponse dto = new EnderecoResponse();
        dto.setId(entity.getId());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setNome(entity.getNome());
        dto.setCep(entity.getCep());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        dto.setUsuario(EnderecoResponse.Usuario.builder()
                .id(entity.getUsuario().getId())
                .nome(entity.getUsuario().getNome())
                .build());
        return dto;
    }

    public static List<EnderecoResponse> toDto(List<Endereco> entities){
        return entities.stream()
                .map(EnderecoMapper::toDto)
                .toList();
    }

    public static Endereco toEntity(EnderecoRequest dto){
        if (dto == null) return null;

        Endereco entity = new Endereco();
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setCep(dto.getCep());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        return entity;
    }
}
