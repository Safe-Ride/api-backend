package saferide.sptech.apibackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Trajeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTrajeto;
    private String escola;
    private int tipo;

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

}
