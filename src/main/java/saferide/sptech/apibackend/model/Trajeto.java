package saferide.sptech.apibackend.model;

import java.util.List;

public class Trajeto {
    private int idTrajeto;
    private String escola;
    private int tipo;
    private List<Endereco> enderecos;

    public int getIdTrajeto() {
        return idTrajeto;
    }

    public void setIdTrajeto(int idTrajeto) {
        this.idTrajeto = idTrajeto;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
