package saferide.sptech.apibackend.dto.cliente;

import saferide.sptech.apibackend.entity.Cliente;

import java.util.List;

public class ClienteMapper {
    public static ClienteListagemDto toDto(Cliente entity){
        if (entity == null) return null;

        ClienteListagemDto dto = new ClienteListagemDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setTipo(entity.getTipo());
        return dto;

    }

    public static Cliente toEntity(ClienteCriacaoDto dto){
        if (dto == null) return null;

        Cliente entity = new Cliente();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setCpf(dto.getCpf());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setTipo(dto.getTipo());
        return entity;
    }

    public static Cliente toEntityAtt(ClienteAtualizacaoDto dto, Cliente entity){
        if (dto == null) return null;

        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setCpf(dto.getCpf());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        return entity;
    }

    public static List<ClienteListagemDto> toDto(List<Cliente> entities){
        return entities.stream()
                .map(ClienteMapper::toDto)
                .toList();
    }
}
