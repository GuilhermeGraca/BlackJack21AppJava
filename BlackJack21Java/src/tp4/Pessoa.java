package tp4;

public abstract class Pessoa {
    //protected de modo a que apenas as classes filhas tenham acesso a este atributo
    //garando controlo de acesso e encapsulamento
    protected Mao mao;

    //Cada pessoa tem uma mão
    public Pessoa() {
        this.mao = new Mao();
    }

    public void adicionarCarta(Carta carta) {
        mao.adicionarCarta(carta);
    }
    public int getValorMao() {
        return mao.getValorMao();
    }

    public String toString() {
        return mao.toString() + " (Valor da mão : )" + getValorMao() + ")";
    }
}
