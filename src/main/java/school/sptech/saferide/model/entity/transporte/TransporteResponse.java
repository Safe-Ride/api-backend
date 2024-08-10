package school.sptech.saferide.model.entity.transporte;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransporteResponse {
    private Integer id;
    private String placa;
    private String cnpj;
    private String cnh;
    private String crm;
    private String crmc;
    private Usuario usuario;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Usuario {
        private Integer id;
        private String nome;
    }
}
