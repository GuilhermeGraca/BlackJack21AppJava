package tp4;

//Classe utilizada para armazenar resultados em objetos com os devidos atributos
public class Resultado {
    private Mao maoJogador;
    private Mao maoDealer;
    private String vencedor;

    public Resultado(Mao maoJogador, Mao maoDealer, String vencedor) {
        this.maoJogador = maoJogador;
        this.maoDealer = maoDealer;
        this.vencedor = vencedor;
    }

    public Mao getMaoJogador() {
        return maoJogador;
    }

    public Mao getMaoDealer() {
        return maoDealer;
    }

    public String getVencedor() {
        return vencedor;
    }
}
