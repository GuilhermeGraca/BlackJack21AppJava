package tp4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BlackJackGUI extends JFrame {


    //está defenido em atributo o que vai ser usado fora do construtor em funções
    private JLabel bancoLabel;
    private JLabel apostaLabel;
    private JLabel labelDinheiroJogador;
    private JLabel labelDinheiroDealer;
    private JPanel cartasDJogadorPanel;
    private JPanel cartasDDealerPanel;
    private Baralho baralho;
    private Jogador jogador;
    private Dealer dealer;

    private ArrayList<String> listaDResultados;

    public BlackJackGUI() {
        // FRAME DA JANELA PRINCIPAL
        setTitle("Black Jack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(670, 620);

        // Inicializar jogo
        baralho = new Baralho();
        int dinheiroTotal = XMLdinheiroJogador.carregarDinheiroTotalJogador();
        jogador = new Jogador(dinheiroTotal);
        if(jogador.getDinheiroTotal() <= 0){
            jogador.injetarDinheiro(1000);
            JOptionPane.showMessageDialog(null, "Dinheiro insufeciente para jogar \n" +
                    "Foi lhe fornecido um saldo de 1000€");
        }
        dealer = new Dealer();
        listaDResultados = new ArrayList<>();
        XMLResultados.inicializarDocumento();


        // Panel Principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0x2e7d32));

            // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0x004d40));

        JLabel tituloLabel = new JLabel("BLACKJACK", JLabel.CENTER);
        tituloLabel.setForeground(Color.WHITE); // cor do texto
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(tituloLabel, BorderLayout.NORTH);
                //Barra de Navegação - Panel
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        navPanel.setBackground(new Color(0x004d40));
                //Botões da navbar
        JButton regrasButton = new JButton("Regras");
        regrasButton.setBackground(new Color(0x004d40));
        regrasButton.setForeground(Color.WHITE);
        regrasButton.setFont(new Font("Arial", Font.BOLD, 20));
        regrasButton.setFocusPainted(false);
        regrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameRegras().setVisible(true);
            }
        });
        navPanel.add(regrasButton);

        JButton dealButton = new JButton("JOGAR");
        dealButton.setBackground(new Color(0x004d40)); //0xffc107 ??
        dealButton.setForeground(Color.WHITE);
        dealButton.setFont(new Font("Arial", Font.BOLD, 20));
        dealButton.setFocusPainted(false);
        dealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jogador.getApostaAtual() > 0){
                    JOptionPane.showMessageDialog(null, "Voçe já se encontra em jogo");
                    return;
                }
                //Cria um frame aposta passando se a si mesmo como parametro
                new FrameAposta(BlackJackGUI.this).setVisible(true);
            }
        });
        navPanel.add(dealButton);

        JButton resultadosButton = new JButton("Resultados");
        resultadosButton.setBackground(new Color(0x004d40));
        resultadosButton.setForeground(Color.WHITE);
        resultadosButton.setFont(new Font("Arial", Font.BOLD, 20));
        resultadosButton.setFocusPainted(false);
        resultadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrameResultados(listaDResultados).setVisible(true);
            }
        });
        navPanel.add(resultadosButton);
        headerPanel.add(navPanel, BorderLayout.SOUTH);

        //Corpo Principal
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(new Color(0x004d40)); // 0x2e7d32

        //Linha separadora 1
        JPanel separator1 = new JPanel();
        separator1.setPreferredSize(new Dimension(670, 1));
        separator1.setBackground(Color.WHITE);
        centralPanel.add(separator1);
        JPanel separatorTransparente = new JPanel();
        separatorTransparente.setPreferredSize(new Dimension(670, 3));
        separatorTransparente.setBackground(new Color(0x004d40));
        centralPanel.add(separatorTransparente);

            // Painel do dealer
        JPanel dealerPanel = new JPanel(new BorderLayout());
        dealerPanel.setBackground(new Color(0x004d40));
        dealerPanel.setPreferredSize(new Dimension(670, 270));
        labelDinheiroDealer = new JLabel("Total Dealer: X", JLabel.CENTER);
        labelDinheiroDealer.setForeground(Color.WHITE);
        labelDinheiroDealer.setFont(new Font("Arial", Font.BOLD, 24));
        dealerPanel.add(labelDinheiroDealer, BorderLayout.NORTH);

        cartasDDealerPanel = new JPanel(new FlowLayout());
        cartasDDealerPanel.setBackground(new Color(0x004d40));
        dealerPanel.add(cartasDDealerPanel, BorderLayout.CENTER);

        centralPanel.add(dealerPanel);

            // Painel do jogador
        JPanel jogadorPanel = new JPanel(new BorderLayout());
        jogadorPanel.setBackground(new Color(0x004d40));
        jogadorPanel.setPreferredSize(new Dimension(670, 270));
        labelDinheiroJogador = new JLabel("Total Jogador: ", JLabel.CENTER);
        labelDinheiroJogador.setForeground(Color.WHITE);
        labelDinheiroJogador.setFont(new Font("Arial", Font.BOLD, 24));
        jogadorPanel.add(labelDinheiroJogador, BorderLayout.NORTH);

        cartasDJogadorPanel = new JPanel(new FlowLayout());
        cartasDJogadorPanel.setBackground(new Color(0x004d40));
        jogadorPanel.add(cartasDJogadorPanel, BorderLayout.CENTER);
        centralPanel.add(jogadorPanel);

            // Painel de ações
        JPanel actionsPanel = new JPanel(new FlowLayout());
        actionsPanel.setBackground(new Color(0x004d40));
        actionsPanel.setPreferredSize(new Dimension(670, 50));
                //Botões de ação
        JButton pedirButton = new JButton("PEDIR");
        pedirButton.setBackground(new Color(0x198754));
        pedirButton.setForeground(Color.WHITE);
        pedirButton.setFont(new Font("Arial", Font.BOLD, 24));
        pedirButton.setFocusPainted(false);
        pedirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para pedir carta
                if(jogador.getApostaAtual() <= 0){
                    JOptionPane.showMessageDialog(null, "Voçe quem que iniciar uma jogada para poder pedir carta \n " +
                            "Pressione o botão JOGAR");
                    return;
                }
                jogador.adicionarCarta(baralho.tirarCarta());
                atualizarLabels();
                if (jogador.getValorMao() > 21) {
                    JOptionPane.showMessageDialog(null, "Você estourou!");
                    determinarVencedor();
                    devolverCartasAoBaralho();
                }
            }
        });

        JButton manterButton = new JButton("MANTER");
        manterButton.setBackground(new Color(0xdc3545));
        manterButton.setForeground(Color.WHITE);
        manterButton.setFont(new Font("Arial", Font.BOLD, 24));
        manterButton.setFocusPainted(false);
        manterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jogador.getApostaAtual() <= 0){
                    JOptionPane.showMessageDialog(null, "Você tem que iniciar uma jogada para poder manter \n " +
                            "Pressione o botão JOGAR");
                    return;
                }
                turnoDoDealer();
            }
        });

        actionsPanel.add(pedirButton);
        actionsPanel.add(manterButton);
        centralPanel.add(actionsPanel);

        // Linha separadora 2
        JPanel separator2 = new JPanel();
        separator2.setPreferredSize(new Dimension(670, 1));
        separator2.setBackground(Color.WHITE);
        centralPanel.add(separator2);

            //Rodapé
        JPanel footerPanel = new JPanel(new GridLayout(1, 2));
        footerPanel.setBackground(new Color(0x004d40));
        footerPanel.setPreferredSize(new Dimension(670, 40));
        bancoLabel = new JLabel("Banco: " + dinheiroTotal + "€", JLabel.CENTER);
        bancoLabel.setForeground(Color.WHITE);
        bancoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        footerPanel.add(bancoLabel);
        apostaLabel = new JLabel("Aposta Atual: X", JLabel.CENTER);
        apostaLabel.setForeground(Color.WHITE);
        apostaLabel.setFont(new Font("Arial", Font.BOLD, 24));
        footerPanel.add(apostaLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centralPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }

    public void iniciarJogo(int aposta) {
        jogador.fazerApostaGUI(aposta);
        distribuirCartasIniciais();
        jogador.mao.calcularValorMao();
        dealer.mao.calcularValorMao();
        atualizarLabels();
    }

    private void atualizarLabels() {
        jogador.mao.calcularValorMao();
        dealer.mao.calcularValorMao();

        bancoLabel.setText("Banco: " + jogador.getDinheiroTotal() + " €");
        apostaLabel.setText("Aposta Atual: " + jogador.getApostaAtual() + " €");
        labelDinheiroJogador.setText("Total Jogador: " + jogador.getValorMao());
        labelDinheiroDealer.setText("Total Dealer: " + dealer.getValorMao());

        atualizarCartas(cartasDJogadorPanel, jogador.mao.getMao());
        atualizarCartas(cartasDDealerPanel, dealer.mao.getMao());
    }

    private void distribuirCartasIniciais() {
        baralho.baralhar();
        jogador.adicionarCarta(baralho.tirarCarta());
        jogador.adicionarCarta(baralho.tirarCarta());
        dealer.adicionarCarta(baralho.tirarCarta());
    }


    private void turnoDoDealer() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (dealer.pedeCarta()) {
                    dealer.adicionarCarta(baralho.tirarCarta());
                    dealer.mao.calcularValorMao();
                    SwingUtilities.invokeLater(() -> atualizarLabels());
                    Thread.sleep(1000); // Atraso de 1 segundo entre cada carta
                    if (dealer.getValorMao() > 21) {
                        break;
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                determinarVencedor();
                devolverCartasAoBaralho();
            }
        };
        worker.execute();
    }

    private void determinarVencedor() {
        int valorJogador = jogador.getValorMao();
        int valorDealer = dealer.getValorMao();

        String vencedor;
        if (valorJogador > 21) {
            vencedor = "Dealer";
            jogador.perdeAposta();
        } else if (valorDealer > 21 || valorJogador > valorDealer) {
            vencedor = "Jogador";
            jogador.ganhaAposta();
        } else if (valorJogador < valorDealer) {
            vencedor = "Dealer";
            jogador.perdeAposta();
        } else {
            vencedor = "Empate";
            jogador.empateAposta();
        }


        String resultado = "RESULTADO:\n" +
                "Mão do Jogador: " + jogador.mao.getMao() + "\n" +
                "Mão do Dealer: " + dealer.mao.getMao() + "\n" +
                "Vencedor: " + vencedor + "\n";
        listaDResultados.add(resultado);

        Resultado resultadoObj = new Resultado(jogador.mao, dealer.mao, vencedor);
        XMLResultados.salvarResultado(resultadoObj);
        XMLdinheiroJogador.guardarDinheiroTotalJogador(jogador.getDinheiroTotal());
        JOptionPane.showMessageDialog(this, "O vencedor é: " + vencedor);
    }

    private void devolverCartasAoBaralho() {
        while (jogador.mao.getMao().size() > 0) {
            baralho.devolverCarta(jogador.mao.tirarCarta());
        }
        while (dealer.mao.getMao().size() > 0) {
            baralho.devolverCarta(dealer.mao.tirarCarta());
        }
        baralho.baralhar();
        atualizarLabels();
    }

    private void atualizarCartas(JPanel panel, ArrayList<Carta> cartas) {
        panel.removeAll();
        for (Carta carta : cartas) {
            String caminhoIMG = "cartasPNG/" + getImagemCarta(carta) + ".png";
            ImageIcon icon = new ImageIcon(caminhoIMG);
            Image imagem = icon.getImage();
            Image novaImg = imagem.getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(novaImg);
            JLabel label = new JLabel(icon);
            panel.add(label);
        }
        panel.revalidate(); //garante com que as imgs recem adicionadas sejam bem exibitas
        //garante q o panel ajusta a sua disposição tendo em conta as novas cartas
        panel.repaint();
    }
    private String getImagemCarta(Carta carta) {
        String valor = "";
        switch (carta.getValor()) {
            case 11: valor = "jack";
            break;
            case 12: valor = "queen";
            break;
            case 13: valor = "king";
            break;
            case 14: valor = "ace";
            break;
            default: valor = String.valueOf(carta.getValor());
            break;
        }
        String naipe = "";
        switch (carta.getNaipe()) {
            case "Copas": naipe = "hearts";
            break;
            case "Espadas": naipe = "spades";
            break;
            case "Paus": naipe = "clubs";
            break;
            case "Ouros": naipe = "diamonds";
            break;
        }
        return valor + "_of_" + naipe;
    }

    public int getDinheiroDoJogador() {
        //Serve para o frame de aposta ter acesso ao saldo do jogador
        return jogador.getDinheiroTotal();
    }

    public static void main(String[] args) {
        new BlackJackGUI();
    }
}

