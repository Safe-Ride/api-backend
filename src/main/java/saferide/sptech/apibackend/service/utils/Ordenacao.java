package saferide.sptech.apibackend.service.utils;

import saferide.sptech.apibackend.entity.Mensagem;
import saferide.sptech.apibackend.entity.Trajeto;

import java.util.List;

public class Ordenacao {
    public static List<Trajeto> quickSort(List<Trajeto> v, int indInicio, int indFim) {
        int i, j;
        Trajeto pivo;
        pivo = v.get(indFim);
        i = indFim;
        for (j = indFim - 1; j >= indInicio; j--) {
            if (v.get(j).getDiaSemana().ordinal() > pivo.getDiaSemana().ordinal()) {
                i = i - 1;
                Trajeto aux = v.get(i);
                v.set(i, v.get(j));
                v.set(j, aux);
            }
        }
        Trajeto aux2 = v.get(indFim);
        v.set(indFim,  v.get(i));
        v.set(i, aux2);

        if (indInicio < i) {
            quickSort(v, indInicio, i - 1);
        }
        if (i < indFim) {
            quickSort(v, i + 1, indFim);
        }
        return v;
    }

}
