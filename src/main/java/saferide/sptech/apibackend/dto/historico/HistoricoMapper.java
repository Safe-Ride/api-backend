package saferide.sptech.apibackend.dto.historico;

import saferide.sptech.apibackend.entity.Historico;

import java.util.stream.Collectors;

public class HistoricoMapper {

    public static HistoricoResponse toDto(Historico entity) {
        if (entity == null) return null;

        HistoricoResponse dto = new HistoricoResponse();
        dto.setId(entity.getId());
        dto.setResponsavel(HistoricoResponse.Usuario.builder()
                .id(entity.getResponsavel().getId())
                .nome(entity.getResponsavel().getNome())
                .telefone(entity.getResponsavel().getTelefone())
                .build());
        dto.setMotorista(HistoricoResponse.Usuario.builder()
                .id(entity.getMotorista().getId())
                .nome(entity.getMotorista().getNome())
                .telefone(entity.getMotorista().getTelefone())
                .build());
        if (entity.getMensagens() != null) {
            dto.setMensagems(entity.getMensagens().stream()
                    .map(s -> HistoricoResponse.Mensagem.builder()
                            .id(s.getId())
                            .data(s.getData())
                            .status(s.getStatus())
                            .usuarioId(s.getUsuario().getId())
                            .dependente(HistoricoResponse.Mensagem.Dependente.builder()
                                    .id(s.getDependente().getId())
                                    .nome(s.getDependente().getNome())
                                    .build())
                            .build())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static Historico toEntity(HistoricoRequest dto) {
        if (dto == null) return null;

        return new Historico();
    }

}
