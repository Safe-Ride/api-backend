package school.sptech.saferide.constants;

public class ControllerConstants {
    public final static String DEPENDENTES_BASE_PATH = "/dependentes";
    public final static String ENDERECO_BASE_PATH = "/enderecos";
    public final static String ESCOLAS_BASE_PATH = "/escolas";
    public final static String CONVERSA_BASE_PATH = "/conversas";
    public final static String MENSAGEM_BASE_PATH = "/mensagens";
    public final static String CONTRATO_BASE_PATH = "/contratos";
    public final static String CONTRATO_POR_MOTORISTA_BASE_PATH = "/motorista/{motoristaId}";
    public final static String CONTRATO_POR_RESPONSAVEL_BASE_PATH = "/responsavel/{responsavelId}";
    public final static String CONTRATO_POR_DEPENDENTE_BASE_PATH = "/dependente/{dependenteId}";
    public final static String PAGAMENTO_BASE_PATH = "/pagamentos";
    public final static String ROTA_BASE_PATH = "/rotas";
    public final static String TRAJETO_BASE_PATH = "/trajetos";
    public final static String TRANSPORTE_BASE_PATH = "/transportes";
    public final static String TRANSPORTE_ESCOLA_BASE_PATH = "/transportes-escolas";
    public final static String TRANSPORTE_ESCOLA_LIST_BY_TRANSPORTE_BASE_PATH = "/transporte/{transporteId}";
    public final static String TRANSPORTE_ESCOLA_LIST_BY_ESCOLA_BASE_PATH = "/escola/{escolaId}";
    public final static String USUARIO_BASE_PATH = "/usuarios";
    public final static String DEPENDENTE_LINK_MOTORISTA_PATH = "/{dependenteId}/vincular-motorista/{motoristaId}";
    public final static String SEARCH_CEP = "/buscar-cep/{cep}";
    public final static String ENDERECO_LIST_BY_USUARIO_PATH = "/usuario/{usuarioId}";
    public final static String LIST_BY_ID_PATH = "/{id}";
    public final static String REMOVE_PATH = "/{id}";
    public final static String SECURITY_NAME = "Bearer";
}
