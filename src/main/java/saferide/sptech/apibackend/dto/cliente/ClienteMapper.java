package saferide.sptech.apibackend.dto.cliente;

import saferide.sptech.apibackend.entity.Cliente;
import saferide.sptech.apibackend.entity.Endereco;

import java.util.List;

public class ClienteMapper {
    public static ClienteResponse toDto(Cliente entity){
        if (entity == null) return null;

        ClienteResponse dto = new ClienteResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setTipo(entity.getTipo());
        dto.setEndereco(entity.getEndereco());
        return dto;

    }

    public static Cliente toEntity(ClienteRequest dto){
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

    public static Cliente toEntityAtt(ClienteRequestUpdate dto, Cliente entity){
        if (dto == null) return null;

        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setCpf(dto.getCpf());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        return entity;
    }

    public static Cliente toEntityAttEndereco(Cliente entity, Endereco endereco){
        entity.setEndereco(endereco);
        return entity;
    }

    public static List<ClienteResponse> toDto(List<Cliente> entities){
        return entities.stream()
                .map(ClienteMapper::toDto)
                .toList();
    }
}
