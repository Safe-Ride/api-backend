package school.sptech.saferide.model.entity.contrato;

import java.util.List;
import java.util.stream.Collectors;

public class ContratoMapper {
    public static ContratoResponse toDto(Contrato entity){
        if (entity == null) return null;

        ContratoResponse dto = new ContratoResponse();
        dto.setId(entity.getId());
        dto.setMotorista(ContratoResponse.Usuario.builder()
                .id(entity.getMotorista().getId())
                .nome(entity.getMotorista().getNome())
                .foto(entity.getMotorista().getImagem().getCaminho())
                .build());
        dto.setResponsavel(ContratoResponse.Usuario.builder()
                .id(entity.getResponsavel().getId())
                .nome(entity.getResponsavel().getNome())
                .foto(entity.getResponsavel().getImagem().getCaminho())
                .build());
        if (entity.getDependentes() != null) {
            dto.setDependentes(entity.getDependentes().stream()
                    .map(d -> ContratoResponse.Dependente.builder()
                            .id(d.getId())
                            .nome(d.getNome())
                            .build())
                    .collect(Collectors.toList()));
        }
        dto.setValor(entity.getValor());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());
        if (entity.getPagamentos() != null) {
            dto.setPagamentos(entity.getPagamentos().stream()
                    .map(s -> ContratoResponse.Pagamento.builder()
                            .dataVencimento(s.getDataVencimento())
                            .dataEfetuacao(s.getDataEfetuacao())
                            .valor(s.getValor())
                            .tipo(s.getTipo())
                            .status(s.getStatus())
                            .build())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public static List<ContratoResponse> toDto(List<Contrato> entities){
        return entities.stream()
                .map(ContratoMapper::toDto)
                .toList();
    }

//    public static ContratoUpdate toDtoUpdate(Contrato entity) {
//        ContratoUpdate dto = new ContratoUpdate();
//        dto.setId(entity.getId());
//        dto.setDependentesId(entity.getDependentes().stream().map(Dependente::getId).toList());
//        dto.setDataInicio(entity.getDataInicio());
//        dto.setDataFim(entity.getDataFim());
//        dto.setValor(entity.getValor());
//        return dto;
//    }

    public static Contrato toEntity(ContratoRequest dto){
        if (dto == null) return null;

        Contrato entity = new Contrato();
        entity.setValor(dto.getValor());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFim(dto.getDataFim());
        return entity;
    }
}
