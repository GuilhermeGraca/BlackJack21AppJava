package tp4;
import java.util.Scanner;
public class BlackJack implements Jogos {
    //Com a classe BlackJack já é possivel jogar um jogo na consola
    //Bastante util para testar a logica do jogo
    private Baralho baralho;
    private Jogador jogador;
    private Dealer dealer;

    /**
     * Construtor da classe BlackJack, cria um baralho um jogador e um deler.
     *O jogador é criado com o dinheiro armazenado no XML
     * É inicializado o doc XML q armazena os resultados
     */
    public BlackJack() {
        baralho = new Baralho();
        int dinheiroTotal = XMLdinheiroJogador.carregarDinheiroTotalJogador();

        //Caso o jogador n tenha dinheiro então é fornecido um saldo de 1000
        if(dinheiroTotal == 0){
            int injetarSaldo = 1000;
            dinheiroTotal = injetarSaldo;
            System.out.println("Saldo insuficiente para Jogar, foi lhe fornecido 1000€");
        }
        jogador = new Jogador(dinheiroTotal);
        dealer = new Dealer();
        XMLResultados.inicializarDocumento();

    }

    public void mostrarRegras() {
        System.out.println("BLACKJACK");
        System.out.println(" Regras de Jogo: ");
        System.out.println("	-O Jogador recebe 2 cartas e o Dealer recebe 1 carta virada para cima e uma virada para baixo.");
        System.out.println("	-Cada carta vale o seu numero, as imagens valem 10 e o Ás vale 1 ou 11.");
        System.out.println("	-O valor da mão vale o total do valar das cartas.");
        System.out.println("	-O jogador pode pedir cartas ou pode manter as que tem ficando o seu valor de mão atual.");
        System.out.println("	-O dealer pede sempre cartas até que o seu valor seja 17 ou superior.");
        System.out.println("	-O objetivo do jogo é ter um valor total de mão maior que o dealer sem deixar passar do 21.");
        System.out.println("	-Se o jogador e o Dealer tiverem o mesmo valor de mão então á um empate. \n \n");
    }

    public void jogar() {
        mostrarRegras();
        while (true) {
            //Se o jogador n tivr dinheiro então então acaba o jogo
            if (jogador.getDinheiroTotal() <= 0) {
                System.out.println("Você está sem saldo. Jogo terminado.");
                break;
            }
            //turno do do jogador
            jogador.fazerAposta();
            distribuirCartasIniciais();
            System.out.println("Mão do dealer: ");
            dealer.mao.mostrarMao();
            turnoDoJogador();
            //Se não explodir passa ao turno do dealer
            if (jogador.getValorMao() <= 21) {
                turnoDoDealer();
            }
            determinarVencedor();
            System.out.println("Seu saldo atual: " + jogador.getDinheiroTotal());
            devolverCartasAoBaralho();
            System.out.println("Deseja jogar novamente? (S/N)");
            Scanner scanner = new Scanner(System.in);
            String resposta = scanner.nextLine();
            //Se o jogador não desejar jogar novamente então o ciclo é quebrado e a jogada acaba
            if (!resposta.equalsIgnoreCase("S")) {
                break;
            }
            baralho = new Baralho(); // Reembaralhar o baralho para uma nova rodada
        }
        //quando termina a jogada o saldo do jogador é guardado no XML
        XMLdinheiroJogador.guardarDinheiroTotalJogador(jogador.getDinheiroTotal());
    }

    public void distribuirCartasIniciais() {
        jogador.adicionarCarta(baralho.tirarCarta());
        jogador.adicionarCarta(baralho.tirarCarta());
        dealer.adicionarCarta(baralho.tirarCarta());
    }


    public void turnoDoJogador() {
        System.out.println("Sua mão: \n");
        //equanto o jogador pedir cartas ocorre o ciclo (pedecarta() == true)
        while (jogador.pedeCarta()) {
            jogador.adicionarCarta(baralho.tirarCarta());
            jogador.mao.calcularValorMao();
            if (jogador.getValorMao() > 21) {
                System.out.println("Você estourou! ");
                break;
            }
        }
    }

    public void turnoDoDealer() {
        System.out.println("-------------");
        System.out.println("TURNO DO DEALER");
        System.out.println("Mão do dealer: \n");
        dealer.mao.mostrarMao();
        //equanto o dealer pedir cartas ocorre o ciclo (pedecarta() == true)
        while (dealer.pedeCarta()) {
            dealer.adicionarCarta(baralho.tirarCarta());
            dealer.mao.calcularValorMao();
            dealer.mao.mostrarMao();
            if (dealer.getValorMao() > 21) {
                System.out.println("Dealer estourou!");
                break;
            }
        }
    }

    public void determinarVencedor() {

        int valorJogador = jogador.getValorMao();
        int valorDealer = dealer.getValorMao();

        System.out.println("------------");
        System.out.println("RESUMO DE JOGO");
        System.out.println("Sua mão: \n" );
        jogador.mao.mostrarMao();
        System.out.println("");
        System.out.println("Mão do dealer: \n" );
        dealer.mao.mostrarMao();
        System.out.println("");

        String vencedor;

        if (valorJogador > 21) {
            System.out.println("Você perdeu!");
            jogador.perdeAposta();
            vencedor = "Dealer";
        } else if (valorDealer > 21 || valorJogador > valorDealer) {
            System.out.println("Você ganhou!");
            jogador.ganhaAposta();
            vencedor = "Jogador";
        } else if (valorJogador < valorDealer) {
            System.out.println("Você perdeu!");
            jogador.perdeAposta();
            vencedor = "Dealer";
        } else {
            System.out.println("Empate!");
            jogador.empateAposta();
            vencedor = "Empate";
        }

        Resultado resultado = new Resultado(jogador.mao, dealer.mao, vencedor);

        XMLResultados.salvarResultado(resultado);
    }

    public void devolverCartasAoBaralho() {
        // Devolver todas as cartas do jogador ao baralho
        while (jogador.mao.getMao().size() > 0) {
            baralho.devolverCarta(jogador.mao.tirarCarta());
        }
        // Devolver todas as cartas do dealer ao baralho
        while (dealer.mao.getMao().size() > 0) {
            baralho.devolverCarta(dealer.mao.tirarCarta());
        }
    }

    public static void main(String[] args) {
        BlackJack jogo = new BlackJack();
        jogo.jogar();
    }




}
