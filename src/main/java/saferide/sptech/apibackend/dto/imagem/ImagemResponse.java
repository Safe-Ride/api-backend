package saferide.sptech.apibackend.dto.imagem;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagemResponse {

    private Integer id;
    private String caminho;

}
