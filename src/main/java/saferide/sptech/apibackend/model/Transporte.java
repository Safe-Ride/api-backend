package saferide.sptech.apibackend.model;

import jakarta.persistence.*;

@Entity
public class Transporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String placa;
    private String cnpj;
    private String cnh;
    private String crm;
    private String crmc;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Integer fkMotorista;

    public Integer getIdTransporte() {
        return id;
    }

    public void setIdTransporte(Integer idTransporte) {
        this.id = idTransporte;
    }

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

    public Integer getFkMotorista() {
        return fkMotorista;
    }

    public void setFkMotorista(Integer fkMotorista) {
        this.fkMotorista = fkMotorista;
    }
}
