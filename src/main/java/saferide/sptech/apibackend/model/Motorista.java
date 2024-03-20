package saferide.sptech.apibackend.model;

public class Motorista extends Cliente {
    private String placa;
    private String cnpj;
    private String cnh;
    private String crm;
    private String crmc;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCrmc() {
        return crmc;
    }

    public void setCrmc(String crmc) {
        this.crmc = crmc;
    }
}
