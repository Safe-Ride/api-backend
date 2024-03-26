package saferide.sptech.apibackend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Trajeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String escola;
    private int tipo;
    @ManyToMany(mappedBy = "trajetos")
    private Set<Dependente> dependentes = new HashSet<>();

    public int getIdTrajeto() {
        return id;
    }

    public void setIdTrajeto(int id) {
        this.id = id;
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
