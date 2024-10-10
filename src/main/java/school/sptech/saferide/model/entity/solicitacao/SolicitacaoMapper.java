package school.sptech.saferide.model.entity.solicitacao;

import school.sptech.saferide.model.enums.StatusSolicitacao;

import java.util.List;

public class SolicitacaoMapper {

    public static SolicitacaoResponse toDto(Solicitacao entity) {
        if (entity == null) return null;

        SolicitacaoResponse dto = new SolicitacaoResponse();
        dto.setId(entity.getId());
        if (entity.getMotorista() != null) {
            dto.setMotorista(SolicitacaoResponse.Motorista.builder()
                    .id(entity.getMotorista().getId())
                    .nome(entity.getMotorista().getNome())
                    .build());
        }
        if (entity.getResponsavel() != null) {
            dto.setResponsavel(SolicitacaoResponse.Responsavel.builder()
                    .id(entity.getResponsavel().getId())
                    .nome(entity.getResponsavel().getNome())
                    .build());
        }
        if (entity.getEscola() != null) {
            dto.setEscola(SolicitacaoResponse.Escola.builder()
                    .id(entity.getEscola().getId())
                    .nome(entity.getEscola().getNome())
                    .build());
        }
        if (entity.getEndereco() != null) {
            dto.setEndereco(SolicitacaoResponse.Endereco.builder()
                    .id(entity.getEndereco().getId())
                    .nome(entity.getEndereco().getCep())
                    .build());
        }
        if (entity.getDependente() != null) {
            dto.setDependente(SolicitacaoResponse.Dependente.builder()
                    .id(entity.getDependente().getId())
                    .nome(entity.getDependente().getNome())
                    .build());
        }
        dto.setPeriodo(entity.getPeriodo());
        dto.setValor(entity.getValor());
        dto.setHorarioIda(entity.getHorarioIda());
        dto.setHorarioVolta(entity.getHorarioVolta());
        dto.setContratoInicio(entity.getContratoInicio());
        dto.setContratoFim(entity.getContratoFim());
        dto.setTipo(entity.getTipo());
        dto.setDiaSemana(entity.getDiaSemana());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static List<SolicitacaoResponse> toDto(List<Solicitacao> entities) {
        return entities.stream()
                .map(SolicitacaoMapper::toDto)
                .toList();
    }

    public static Solicitacao toEntity(SolicitacaoRequest dto){
        if (dto == null) return null;

        Solicitacao entity = new Solicitacao();
        entity.setPeriodo(dto.getPeriodo());
        entity.setValor(dto.getValor());
        entity.setHorarioIda(dto.getHorarioIda());
        entity.setHorarioVolta(dto.getHorarioVolta());
        entity.setContratoInicio(dto.getContratoInicio());
        entity.setContratoFim(dto.getContratoFim());
        entity.setTipo(dto.getTipo());
        entity.setDiaSemana(dto.getDiaSemana());
        entity.setStatus(StatusSolicitacao.PENDENTE);
        return entity;
    }
}
