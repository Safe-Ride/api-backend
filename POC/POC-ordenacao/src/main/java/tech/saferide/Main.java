package tech.saferide;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        VetorOrdenacao vet = new VetorOrdenacao();
        System.out.println(Arrays.toString(vet.gerarNumerosAleatorios()));
        System.out.println(Arrays.toString(vet.ordenarVetor()));
        pesquisaBinaria pesquisa = new pesquisaBinaria();
        System.out.println(pesquisa.pesquisarNumero(25));
    }
}