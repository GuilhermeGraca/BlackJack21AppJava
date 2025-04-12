package tp4;

import java.util.ArrayList;
public class Mao {
    private ArrayList<Carta> cartasMao;
    private int valorMao;
    public Mao(){
        cartasMao = new ArrayList<>();
        valorMao = 0;
    }


    public void adicionarCarta(Carta carta) {
        cartasMao.add(carta);
    }

    public void calcularValorMao() {
        //Para evitar erros também é utilizado uma variavel temporaria para calcular o valor da mão
        //o valor começa por voltar a 0 para não haver erros ao recalcular o valor ao longo do jogo
        int valorMaoTemp = 0;

        //adiciona o valor de cada carta ao valor da mao
        for (Carta carta : cartasMao){
            valorMaoTemp += carta.getValorEmJogo();
        }

        int numeroDeAses = 0;

        //conta o numero de ases na mão
        for(Carta carta : cartasMao){
            if(carta.getValor() == 14){
                numeroDeAses++;
            }
        }

        //Enquando o valor da mão for mais que 21 e houver Ases na mão:
        while(valorMaoTemp > 21 && numeroDeAses >0){
            valorMaoTemp = valorMaoTemp - 10; //ao tirar 10 está a passar o valor do Ás de 11 para 1
            numeroDeAses--;
        }

        valorMao = valorMaoTemp;
    }

    public int getValorMao() {
        return valorMao;
    }

    public ArrayList<Carta> getMao() {
        return cartasMao;
    }

    public Carta tirarCarta() {
        if (cartasMao.isEmpty()) {
            return null; // Retorna null se não houver cartas na mão
        }
        return cartasMao.remove(cartasMao.size() - 1); // Remove e retorna a última carta da mão
    }


    //Mostra na consola as cartas que se tem na mão
    //Serve apenas para uso na classe BLackJack
    public void mostrarMao() {
        calcularValorMao();
        System.out.println("Cartas: " + cartasMao + " Valor total: " + valorMao);
    }
}
