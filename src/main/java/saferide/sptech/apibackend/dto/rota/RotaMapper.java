package saferide.sptech.apibackend.dto.rota;

import saferide.sptech.apibackend.dto.dependente.DependenteMapper;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.dto.endereco.ViaCepResponse;
import saferide.sptech.apibackend.dto.trajeto.TrajetoMapper;
import saferide.sptech.apibackend.dto.trajeto.TrajetoResponse;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.entity.Rota;
import saferide.sptech.apibackend.entity.Trajeto;
import saferide.sptech.apibackend.entity.id.RotaId;

public class RotaMapper {

    public static Rota toEntity(RotaRequest request) {
        Rota rota = new Rota();
        rota.setDependente(Dependente.builder().id(request.getDependenteId()).build());
        rota.setTrajeto(Trajeto.builder().id(request.getTrajetoId()).build());
        rota.setEndereco(Endereco.builder().id(request.getEnderecoId()).build());

        RotaId id = new RotaId();
        id.setDependenteId(request.getDependenteId());
        id.setTrajetoId(request.getTrajetoId());
        id.setEnderecoId(request.getEnderecoId());

        rota.setId(id);
        return rota;
    }

    public static RotaResponse toDto(Rota entity, ViaCepResponse viaCepResponse) {
        RotaResponse dto = new RotaResponse();
        DependenteResponse dependenteResponse = DependenteMapper.toDto(entity.getDependente());
        TrajetoResponse trajetoResponse = TrajetoMapper.toDto(entity.getTrajeto());
        RotaResponse.Endereco enderecoResponse = RotaResponse.Endereco.builder()
                .id(entity.getEndereco().getId())
                .latitude(entity.getEndereco().getLatitude())
                .longitude(entity.getEndereco().getLongitude())
                .cep(entity.getEndereco().getCep())
                .numero(entity.getEndereco().getNumero())
                .logradouro(viaCepResponse.getLogradouro())
                .complemento(entity.getEndereco().getComplemento())
                .bairro(viaCepResponse.getBairro())
                .localidade(viaCepResponse.getLocalidade())
                .uf(viaCepResponse.getUf())
                .build();

        dto.setDependente(dependenteResponse);
        dto.setEndereco(enderecoResponse);
        dto.setTrajeto(trajetoResponse);

        return dto;

    }

}
