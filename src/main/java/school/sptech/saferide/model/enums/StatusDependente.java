package school.sptech.saferide.model.enums;

public enum StatusDependente {
    NAO_INICIADO("Trajeto não iniciado"),
    INICIADO("Trajeto iniciado"),
    NAO_IRA("Não vai!"),
    NA_VAN("Na van"),
    NA_ESCOLA("Na escola!"),
    CONVERSA_CRIADA(""),
    EM_CASA("Em casa");

    public final String exibicao;

    StatusDependente(String exibicao) {
        this.exibicao = exibicao;
    }
}
