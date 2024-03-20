package saferide.sptech.apibackend.model;

import java.util.ArrayList;
import java.util.List;

public class Responsavel extends Cliente {
    private List<Dependente> dependentes = new ArrayList();

    public void adicionarDependente(Dependente dependente) {
        dependentes.add(dependente);
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }
}
