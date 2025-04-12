package tp4;

import javax.swing.*;
import java.awt.*;

public class FrameRegras extends JFrame {
    public FrameRegras() {
        setTitle("Regras do Black Jack");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JTextArea rulesText = new JTextArea();
        rulesText.setText(
                "REGRAS:\n" +
                "1) O jogador recebe 2 cartas e o Dealer recebe 1 carta virada para cima e uma virada para baixo.\n" +
                "2) Cada carta vale o seu numero, as figuras valem 10 e o As vale 1 ou 11.\n" +
                "3) O jogador pode pedir cartas ou pode manter-se.\n" +
                "4) O dealer pede sempre cartas até que o seu valor seja 17 ou superior.\n" +
                "5) O objetivo do jogo é ter um total de valor maior que o dealer sem deixar passar de 21.\n" +
                "6) O jogador e o Dealer devem ter o mesmo valor de mão então é um empate.");
        rulesText.setEditable(false);
        rulesText.setWrapStyleWord(true);
        rulesText.setLineWrap(true);
        //rulesText.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(rulesText);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
