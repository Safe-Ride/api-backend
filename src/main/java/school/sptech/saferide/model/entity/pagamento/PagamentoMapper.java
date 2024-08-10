package school.sptech.saferide.model.entity.pagamento;

import java.util.List;

public class PagamentoMapper {
    public static PagamentoResponse toDto(Pagamento entity){
        if (entity == null) return null;

        PagamentoResponse dto = new PagamentoResponse();
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDataVencimento(entity.getDataVencimento());
        dto.setDataEfetuacao(entity.getDataEfetuacao());
        dto.setValor(entity.getValor());
        dto.setTipo(entity.getTipo());
        dto.setTipo(entity.getTipo());
        return dto;
    }

    public static List<PagamentoResponse> toDto(List<Pagamento> entities){
        return entities.stream()
                .map(PagamentoMapper::toDto)
                .toList();
    }

    public static Pagamento toEntity(PagamentoRequest dto){
        if (dto == null) return null;

        Pagamento entity = new Pagamento();
        entity.setDataCriacao(dto.getDataCriacao());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setDataEfetuacao(dto.getDataEfetuacao());
        entity.setValor(dto.getValor());
        entity.setTipo(dto.getTipo());
        return entity;
    }
}
