package saferide.sptech.apibackend.constants;

public class ControllerConstants {

    public final static String DEPENDENTES_BASE_PATH = "/dependentes";
    public final static String ENDERECO_BASE_PATH = "/enderecos";
    public final static String ESCOLAS_BASE_PATH = "/escolas";
    public final static String HISTORICO_BASE_PATH = "/historicos";
    public final static String MENSAGEM_BASE_PATH = "/mensagens";
    public final static String ROTA_BASE_PATH = "/rotas";
    public final static String TRAJETO_BASE_PATH = "/trajetos";
    public final static String TRANSPORTE_BASE_PATH = "/transportes";
    public final static String TRANSPORTE_ESCOLA_BASE_PATH = "/transportes-escolas";
    public final static String USUARIO_BASE_PATH = "/usuarios";

    public final static String LINK_DEPENDENTE_WITH_MOTORISTA_PATH = "/{dependenteId}/vincular-motorista/{motoristaId}";
    public final static String SEARCH_CEP = "/buscar-cep/{cep}";

    public final static String LIST_BY_ID_PATH = "/{id}";
    public final static String UPDATE_PATH = "/{id}";
    public final static String REMOVE_PATH = "/{id}";

    public final static String SECURITY_NAME = "Bearer";

}
