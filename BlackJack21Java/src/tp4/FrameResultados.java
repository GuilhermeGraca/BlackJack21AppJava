package tp4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameResultados extends JFrame {
    public FrameResultados(ArrayList<String> listaDResultados) {

        setTitle("Resultados Anteriores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JTextArea resultados = new JTextArea();
        resultados.setEditable(false);
        resultados.setWrapStyleWord(true);
        resultados.setLineWrap(true);

        // Inicializar com os resultados passados
        //É utilizado um string builder pois é optimizado para a concatenação de Strings
        StringBuilder resultadosTexto = new StringBuilder("RESULTADOS ANTERIORES:\n\n");
        //percorre a lista de resultados q está nos parametros e adiciona os à string resultados texto
        for (String resultado : listaDResultados) {
            resultadosTexto.append(resultado).append("---------" + "\n");
        }
        resultados.setText(resultadosTexto.toString());
        //É adicionado um scroll aos resultados
        JScrollPane scrollPane = new JScrollPane(resultados);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }



}

