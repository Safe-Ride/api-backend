package saferide.sptech.apibackend.dto.imagem;

import saferide.sptech.apibackend.entity.Imagem;

import java.util.List;

public class ImagemMapper {

    public static ImagemResponse toDto(Imagem entity){
        if (entity == null) return null;

        ImagemResponse dto = new ImagemResponse();
        dto.setId(entity.getId());
        dto.setCaminho(entity.getCaminho());
        return dto;
    }

    public static List<ImagemResponse> toDto(List<Imagem> entities){
        return entities.stream()
                .map(ImagemMapper::toDto)
                .toList();
    }

    public static Imagem toEntity(ImagemRequest dto){
        if (dto == null) return null;

        Imagem entity = new Imagem();
        entity.setCaminho(dto.getCaminho());
        return entity;
    }

}
