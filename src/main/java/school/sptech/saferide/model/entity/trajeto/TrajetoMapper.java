package school.sptech.saferide.model.entity.trajeto;

import java.util.List;
import java.util.stream.Collectors;

public class TrajetoMapper {

    public static TrajetoResponse toDto(Trajeto entity) {
        if (entity == null) return null;

        TrajetoResponse dto = new TrajetoResponse();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setHorario(entity.getHorario());
        dto.setDiaSemana(entity.getDiaSemana());
        dto.setAtivo(entity.getAtivo());
        if (entity.getEscola() != null) {
            dto.setEscola(TrajetoResponse.Escola.builder()
                    .id(entity.getEscola().getId())
                    .nome(entity.getEscola().getNome())
                    .build());
        }
        if (entity.getMotorista() != null) {
            dto.setMotorista(TrajetoResponse.Motorista.builder()
                    .id(entity.getMotorista().getId())
                    .nome(entity.getMotorista().getNome())
                    .build());
        }
        if (entity.getRotas() != null) {
            dto.setRotas(entity.getRotas().stream()
                    .map(s -> TrajetoResponse.Rota.builder()
                            .id(s.getId())
                            .status(s.getStatus())
                            .dependente(TrajetoResponse.Rota.Dependente.builder()
                                    .id(s.getDependente().getId())
                                    .nome(s.getDependente().getNome())
                                    .build())
                            .endereco(TrajetoResponse.Rota.Endereco.builder()
                                    .id(s.getDependente().getId())
                                    .build())
                            .build())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static List<TrajetoResponse> toDto(List<Trajeto> entities) {
        return entities.stream()
                .map(TrajetoMapper::toDto)
                .toList();
    }

    public static Trajeto toEntity(TrajetoRequest dto){
        if (dto == null) return null;

        Trajeto entity = new Trajeto();
        entity.setTipo(dto.getTipo());
        entity.setHorario(dto.getHorario());
        entity.setDiaSemana(dto.getDiaSemana());
        return entity;
    }
}
