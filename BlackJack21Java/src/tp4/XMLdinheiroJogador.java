package tp4;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLdinheiroJogador {

    public static int carregarDinheiroTotalJogador() {

        int dinheiroTotal = 0;
        try {
            File file = new File("dinheiroTotal.xml");
            if (!file.exists()) {
                return 1000; // Retorna o saldo inicial padrão se o arquivo não existir
            }

            //utiliza se o doc builder e doc factory para criar um objeto doc
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);



            //Obtem se uma lista de elementos com a tag dinheiro total
            NodeList nList = doc.getElementsByTagName("dinheiroTotal");
            //Se a lista contiver elementos, o texto do primeiro elemento é
            // convertido para um inteiro e armazenado em dinheiro total
            if (nList.getLength() > 0) {
                Element element = (Element) nList.item(0);
                dinheiroTotal = Integer.parseInt(element.getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace(); // Caso haja algum erro então essa excessão é imprimida na consola
        }
        return dinheiroTotal;//retorna o dinheiro total do jogador que está no XML
    }

    public static void guardarDinheiroTotalJogador(int dinheiroTotal) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // elemento raiz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("jogador");
            doc.appendChild(rootElement);

            // elemento saldo
            //É filho do elemento jogador e tem como conteudo textual o dinheiro total q foi passado como parametro
            Element saldoElement = doc.createElement("dinheiroTotal");
            saldoElement.appendChild(doc.createTextNode(Integer.toString(dinheiroTotal)));
            rootElement.appendChild(saldoElement);

            // escrever o conteúdo em no arquivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("dinheiroTotal.xml"));

            transformer.transform(source, result);

            System.out.println("Dinheiro Total do Jogador guardado em dinheiroTotal.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
