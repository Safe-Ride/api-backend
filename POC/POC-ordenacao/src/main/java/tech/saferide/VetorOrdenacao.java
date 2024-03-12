package tech.saferide;

import java.util.Random;

public class VetorOrdenacao {

    private int[] vetorIdade = new int[200];


    public int[] gerarNumerosAleatorios(){
        Random randomizacao = new Random();

        for (int i = 0; i < vetorIdade.length; i++) {
            vetorIdade[i] = randomizacao.nextInt(15)+2;
        }
        ordenarVetor();
        if (confimarOrdenacaoVetor()) {
            return vetorIdade;
        }
        return null;
    }

    public int[] ordenarVetor(){
        for (int i = 0; i < vetorIdade.length; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < vetorIdade.length; j++) {
                if (vetorIdade[j] < vetorIdade[indiceMenor]) {
                    indiceMenor = j;

                }

            }
            int aux = vetorIdade[i];
            vetorIdade[i] = vetorIdade[indiceMenor];
            vetorIdade[indiceMenor] = aux;


        }

        return vetorIdade;
    }

    public Boolean confimarOrdenacaoVetor(){
        int trocas = 0;
        for (int i = 0; i < vetorIdade.length; i++) {
            for (int j = 1; j < vetorIdade.length; j++) {
                if (vetorIdade[j-1]> vetorIdade[j]){
                    int aux = vetorIdade[j];
                    vetorIdade[j] = vetorIdade[j-1];
                    vetorIdade[j-1] = aux;
                    trocas++;
                }
                if (trocas > 0){
                    return false;
                }
            }
        }
        return true;
    }

    public int[] getVetorIdade() {
        return vetorIdade;
    }
}
