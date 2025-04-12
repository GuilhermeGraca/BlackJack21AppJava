package tp4;

import java.util.Scanner;
public class Jogador extends Pessoa {
    //ver se dá para tirar
    private Scanner scanner;
    private int dinheiroTotal;
    private int apostaAtual;
    public Jogador(int dinheiroInicial) {

        scanner = new Scanner(System.in);
        this.dinheiroTotal = dinheiroInicial;
        this.apostaAtual = 0;
    }

    //Utilizado apenas na Classe BlackJack
    /**
     * Pergunta ao usuário se quer pedir carta ou não
     * enquanto o jogador responder "p" de pedir então retorna verdadeiro
     */
    public boolean pedeCarta() {
        mao.mostrarMao();
        System.out.println("Pedir ou manter? (P/M)");
        String resposta = scanner.nextLine();
        if(resposta.equalsIgnoreCase("P")){
            return true;
        }else return false;
    }

    //Utilizado apenas na classe BlackJAck
    public void fazerAposta() {
        System.out.println("banco: " + dinheiroTotal);
        System.out.println("Valor da aposta:");
        apostaAtual = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        while (apostaAtual > dinheiroTotal || apostaAtual <= 0) {
            System.out.println("Aposta inválida.");
            apostaAtual = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer
        }
        dinheiroTotal -= apostaAtual; //É retirado o valor da aposta do banc do jogador
    }

    //Utilizado apenas na classe BlackJAckGUI
    public void fazerApostaGUI(int aposta) {
        apostaAtual = aposta;
        dinheiroTotal -= apostaAtual; //É retirado o valor da aposta do banco do jogador
    }

    public void ganhaAposta() {
        dinheiroTotal += 2 * apostaAtual;
        apostaAtual = 0;
    }

    public void empateAposta() {
        dinheiroTotal += apostaAtual;
        apostaAtual = 0;
    }

    public void perdeAposta() {
        apostaAtual = 0;
    }

    public int getDinheiroTotal() {
        return dinheiroTotal;
    }

    public int getApostaAtual(){return apostaAtual; }

    public void injetarDinheiro(int dinheiro){
    //Serve para o GUI usar quando o jogador está sem saldo para jogar
        dinheiroTotal = dinheiro;
    }
}
