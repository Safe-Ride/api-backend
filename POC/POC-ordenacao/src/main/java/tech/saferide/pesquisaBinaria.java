package tech.saferide;

public class pesquisaBinaria extends VetorOrdenacao{

    private int indMenor=0;
    private int indFinal = super.getVetorIdade().length -1;

    private int meio;

    public int pesquisarNumero(int numero){
        super.gerarNumerosAleatorios();
        if (super.confimarOrdenacaoVetor()) {
            while (indMenor <= indFinal) {
                meio = (indFinal + indMenor) / 2;
                if (super.getVetorIdade()[meio] == numero) {
                    return meio;
                } else if (super.getVetorIdade()[meio] < meio) {
                    indMenor = meio + 1;
                } else {
                    indFinal = meio - 1;
                }
            }

            return -1;
        }
        return -1;
    }
}
