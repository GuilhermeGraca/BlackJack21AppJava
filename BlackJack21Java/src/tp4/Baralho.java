package tp4;
import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
    private ArrayList<Carta> cartas;

    /**
     * Método Construtor da Classe Baralho
     */
    public Baralho() {

        cartas = new ArrayList<>();
        String[] naipes = {"Copas", "Espadas", "Paus","Ouros"};

        //Para cada naipe(), são criadas cartas desde o valor 2 até ao Às(14)
        //Criando assim as 52 cartas do baralho
        for(String naipe : naipes){
            //for each loop que itera por cada naipe no array de naipes
            for (int valor = 2; valor <= 14; valor ++){
                cartas.add(new Carta(valor, naipe));
            }
        }
        //Sendo que no fim de criar as cartas elas ficam todas por ordem então é usado o método baralhar;
        baralhar();
    }

    public void baralhar() {
        Collections.shuffle(cartas);
    }
    public Carta tirarCarta() {
        //remove a carta de cima do Deck (Ultima do Array)
        return cartas.remove(cartas.size() - 1);
    }

    public void devolverCarta(Carta carta) {
        cartas.add(carta);
    }

}
