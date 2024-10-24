package school.sptech.saferide.model.entity.usuario;

import school.sptech.saferide.model.autentication.UsuarioTokenDto;

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
                            .responsavel(UsuarioResponse.Dependente.Responsavel.builder()
                                    .id(s.getResponsavel().getId())
                                    .nome(s.getResponsavel().getNome())
                                    .build())
                            .build())
                    .collect(Collectors.toList()));
        }
        if (entity.getImagem() != null) {
            dto.setImagem(UsuarioResponse.Imagem.builder()
                            .id(entity.getImagem().getId())
                            .caminho(entity.getImagem().getCaminho())
                            .build());
        }
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

    public static UsuarioTokenDto login(Usuario usuario, String token){
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

    public static ResponsavelListarMotoristas toListaMotoristas(Usuario entity) {
        if (entity == null) return null;

        ResponsavelListarMotoristas dto = new ResponsavelListarMotoristas();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setFoto(entity.getImagem().getCaminho());
        return dto;
    }

    public static List<ResponsavelListarMotoristas> toListaMotoristas(List<Usuario> entities) {
        return entities.stream()
                .map(UsuarioMapper::toListaMotoristas)
                .toList();
    }
}
