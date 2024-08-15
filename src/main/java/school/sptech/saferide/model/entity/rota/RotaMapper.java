package school.sptech.saferide.model.entity.rota;

import school.sptech.saferide.model.entity.dependente.DependenteMapper;
import school.sptech.saferide.model.entity.dependente.DependenteResponse;
import school.sptech.saferide.model.entity.trajeto.TrajetoMapper;
import school.sptech.saferide.model.entity.trajeto.TrajetoResponse;

public class RotaMapper {
    public static Rota toEntity(RotaRequest request) {
        if (request == null) return null;
        return new Rota();
    }

//    public static RotaResponse toDtoViaCep(Rota entity, ViaCepResponse viaCepResponse) {
//        RotaResponse dto = new RotaResponse();
//        DependenteResponse dependenteResponse = DependenteMapper.toDto(entity.getDependente());
//        TrajetoResponse trajetoResponse = TrajetoMapper.toDto(entity.getTrajeto());
//        RotaResponse.Endereco enderecoResponse = RotaResponse.Endereco.builder()
//                .id(entity.getEndereco().getId())
//                .latitude(entity.getEndereco().getLatitude())
//                .longitude(entity.getEndereco().getLongitude())
//                .cep(entity.getEndereco().getCep())
//                .numero(entity.getEndereco().getNumero())
//                .logradouro(viaCepResponse.getLogradouro())
//                .complemento(entity.getEndereco().getComplemento())
//                .bairro(viaCepResponse.getBairro())
//                .localidade(viaCepResponse.getLocalidade())
//                .uf(viaCepResponse.getUf())
//                .build();
//
//        dto.setId(entity.getId());
//        dto.setDependente(dependenteResponse);
//        dto.setEndereco(enderecoResponse);
//        dto.setTrajeto(trajetoResponse);
//        dto.setStatus(entity.getStatus());
//
//        return dto;
//    }

    public static RotaResponse toDto(Rota entity) {
        RotaResponse dto = new RotaResponse();
        DependenteResponse dependenteResponse = DependenteMapper.toDto(entity.getDependente());
        TrajetoResponse trajetoResponse = TrajetoMapper.toDto(entity.getTrajeto());

        dto.setId(entity.getId());
        dto.setDependente(dependenteResponse);
        dto.setTrajeto(trajetoResponse);
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
