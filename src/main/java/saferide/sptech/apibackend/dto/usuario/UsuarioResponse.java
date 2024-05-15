package saferide.sptech.apibackend.dto.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import saferide.sptech.apibackend.entity.TipoUsuario;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioResponse {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoUsuario tipo;
    private List<Dependente> dependentes;
    private List<Endereco> enderecos;
    private List<Dependente> clientes;
    private Imagem imagem;

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Dependente {

        private Integer id;
        private String nome;
        private LocalDate dataNascimento;
        private Escola escola;
        private String serie;

        @Data
        @Builder
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Escola {

            private Integer id;
            private String nome;

        }

    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Endereco {

        private Integer id;
        private String cep;

    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Imagem {

        private Integer id;
        private String caminho;

    }

}
