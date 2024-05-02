package saferide.sptech.apibackend.dto.transporteEscola;

import saferide.sptech.apibackend.dto.dependente.DependenteMapper;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.dto.endereco.ViaCepResponse;
import saferide.sptech.apibackend.dto.escola.EscolaMapper;
import saferide.sptech.apibackend.dto.trajeto.TrajetoMapper;
import saferide.sptech.apibackend.dto.trajeto.TrajetoResponse;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.entity.id.RotaId;
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

        dto.setTransporte(toTransporteDto(entity.getTransporte()));
        dto.setEscola(toEscolaDto(entity.getEscola()));

        return dto;
    }

    public static List<TransporteEscolaResponse> toDto(List<TransporteEscola> entities) {
        return entities.stream()
                .map(TransporteEscolaMapper::toDto)
                .toList();
    }

    public static TransporteEscolaTransporteResponse toTransporteDto(Transporte entity) {
        TransporteEscolaTransporteResponse dto = new TransporteEscolaTransporteResponse();

        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setCnpj(entity.getCnpj());
        dto.setCnh(entity.getCnh());
        dto.setCrm(entity.getCrm());
        dto.setCrmc(entity.getCrmc());

        return dto;

    }

    public static TransporteEscolaEscolaResponse toEscolaDto(Escola entity) {
        TransporteEscolaEscolaResponse dto = new TransporteEscolaEscolaResponse();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEndereco(EscolaMapper.toEnderecoDto(entity.getEndereco()));

        return dto;

    }

}
