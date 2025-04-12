package tp4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAposta extends JFrame {
    private BlackJackGUI guiBlackJack;

    public FrameAposta(BlackJackGUI guiBlackJack) {
        this.guiBlackJack = guiBlackJack;

        setTitle("Introduza a sua aposta");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal
        JPanel mainPanel = new JPanel(new GridLayout(3,1));

        // Título
        JLabel titleLabel = new JLabel("Introduza a sua aposta", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel);

        // Painel central
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextField zonaDAposta = new JTextField(10);
        centerPanel.add(zonaDAposta);
        JLabel eurosLabel = new JLabel("€");
        eurosLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(eurosLabel);

        mainPanel.add(centerPanel);

        // Painel de envio
        JPanel enviarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton enviarButton = new JButton("Enviar");
        enviarButton.setBackground(new Color(0x198754));
        enviarButton.setForeground(Color.WHITE);
        enviarButton.setFont(new Font("Arial", Font.BOLD, 14));
        enviarButton.setFocusPainted(false);
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoAposta = zonaDAposta.getText();
                int aposta = Integer.parseInt(textoAposta);
                if (aposta > guiBlackJack.getDinheiroDoJogador()){
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                    return;
                } else if (aposta <= 0){
                    JOptionPane.showMessageDialog(null, "Aposta invalida");
                    return;
                }
                guiBlackJack.iniciarJogo(aposta);
                dispose(); // Fechar a janela de aposta
            }
        });
        enviarPanel.add(enviarButton);


        mainPanel.add(enviarPanel);

        add(mainPanel);
    }
}
