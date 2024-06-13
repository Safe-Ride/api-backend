package saferide.sptech.apibackend.dto.usuario;

import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.service.autentication.UsuarioTokenDto;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioResponse toDto(Usuario entity) {
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
            dto.setDependentes(entity.getDependentes().stream()
                    .map(s -> UsuarioResponse.Dependente.builder()
                            .id(s.getId())
                            .nome(s.getNome())
                            .dataNascimento(s.getDataNascimento())
                            .escola(UsuarioResponse.Dependente.Escola.builder()
                                    .id(s.getEscola().getId())
                                    .nome(s.getEscola().getNome())
                                    .build())
                            .serie(s.getSerie())
                            .build())
                    .collect(Collectors.toList()));
        }
        if (entity.getEnderecos() != null) {
            dto.setEnderecos(entity.getEnderecos().stream()
                    .map(s -> UsuarioResponse.Endereco.builder()
                            .id(s.getId())
                            .cep(s.getCep())
                            .build())
                    .collect(Collectors.toList()));
        }
        if (entity.getClientes() != null) {
            dto.setClientes(entity.getClientes().stream()
                    .map(s -> UsuarioResponse.Dependente.builder()
                            .id(s.getId())
                            .nome(s.getNome())
                            .dataNascimento(s.getDataNascimento())
                            .escola(UsuarioResponse.Dependente.Escola.builder()
                                    .id(s.getEscola().getId())
                                    .nome(s.getEscola().getNome())
                                    .build())
                            .serie(s.getSerie())
                            .build())
                    .collect(Collectors.toList()));
        }
        dto.setImagem(UsuarioResponse.Imagem.builder()
                .id(entity.getImagem().getId())
                .caminho(entity.getImagem().getCaminho())
                .build());
        return dto;
    }

    public static List<UsuarioResponse> toDto(List<Usuario> entities) {
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

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setToken(token);
        usuarioTokenDto.setUsuarioId(usuario.getId());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setCpf(usuario.getCpf());
        usuarioTokenDto.setTelefone(usuario.getTelefone());
        usuarioTokenDto.setDataNascimento(usuario.getDataNascimento());
        usuarioTokenDto.setTipo(usuario.getTipo());
        usuarioTokenDto.setFoto(usuario.getImagem().getCaminho());

        return usuarioTokenDto;
    }

}
