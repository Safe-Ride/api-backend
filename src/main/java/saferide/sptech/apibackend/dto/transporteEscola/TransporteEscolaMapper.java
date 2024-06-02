package saferide.sptech.apibackend.dto.transporteEscola;

import saferide.sptech.apibackend.dto.escola.EscolaMapper;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.entity.id.TransporteEscolaId;

import java.util.List;

public class TransporteEscolaMapper {

    public static TransporteEscola toEntity(TransporteEscolaRequest request) {
        if (request == null) return null;

        TransporteEscola entity = new TransporteEscola();

        TransporteEscolaId id = new TransporteEscolaId();
        id.setTransporteId(request.getTransporteId());
        id.setEscolaId(request.getEscolaId());

        entity.setId(id);
        return entity;
    }

    public static TransporteEscolaResponse toDto(TransporteEscola entity) {
        TransporteEscolaResponse dto = new TransporteEscolaResponse();

        dto.setTransporte(TransporteEscolaResponse.Transporte.builder()
                .id(entity.getTransporte().getId())
                .morotistaId(entity.getTransporte().getUsuario().getId())
                .build());
        dto.setEscola(TransporteEscolaResponse.Escola.builder()
                .id(entity.getEscola().getId())
                .nome(entity.getEscola().getNome())
                .build());

        return dto;
    }

    public static List<TransporteEscolaResponse> toDto(List<TransporteEscola> entities) {
        return entities.stream()
                .map(TransporteEscolaMapper::toDto)
                .toList();
    }

}
