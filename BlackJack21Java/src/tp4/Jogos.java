package tp4;

/**
 * Nesta interface "Jogos", estão defenidos métodos que todas as classes
 * que representam jogos de Blackjack devem implementar
 */
public interface Jogos {
    void jogar();
    void mostrarRegras();
    void distribuirCartasIniciais();
    void turnoDoJogador();
    void turnoDoDealer();
    void determinarVencedor();
}
