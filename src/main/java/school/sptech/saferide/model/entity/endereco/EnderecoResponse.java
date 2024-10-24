package school.sptech.saferide.model.entity.endereco;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoResponse {
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String nome;
    private String cep;
    private Integer numero;
    private String complemento;
    private Usuario usuario;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Usuario {
        private int id;
        private String nome;
    }
}
