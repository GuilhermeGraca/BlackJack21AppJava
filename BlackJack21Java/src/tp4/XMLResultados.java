package tp4;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class XMLResultados {

    //atributo com o nome do documento
    private static final String nomeFicheiro = "resultados.xml";

    public static void inicializarDocumento() {
        try {

            File file = new File(nomeFicheiro);
            //se o não ficheiro existir
            if (!file.exists()) {
                //Cria se um doc builder factory um doc build
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                //utiliza se o DocumentBuilderFactory para criar um DocumentBuilder
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();//cria o doc XML

                Element rootElement = doc.createElement("BlackJack"); //elemento raiz BlackJack
                doc.appendChild(rootElement);

                //cria se um transforme factory para criar um transformer para aplicar transformações ao doc XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                //aplica se as transformações de identação
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                //resultado
                DOMSource source = new DOMSource(doc); //fonte
                StreamResult result = new StreamResult(new File(nomeFicheiro)); //resultado
                transformer.transform(source, result); // transforma e salva o doc no arquivo
            }
        } catch (Exception e) {
            e.printStackTrace();//caso haja erros imprimido a exceção q ocorreu
        }
    }

    public static void salvarResultado(Resultado resultado) {
        try {
            //cria um obj File com o nome do ficheiro
            File file = new File(nomeFicheiro);
            //cria se um docFactory para criar um docBuilder
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file); //transforma os dados do XML para q possam ser usados

            //Criam se os elementos e adicionam se ao "pais"
            Element resultadoElement = doc.createElement("Resultado");
            doc.getDocumentElement().appendChild(resultadoElement);

            Element jogadorElement = doc.createElement("Jogador");
            resultadoElement.appendChild(jogadorElement);

            Element maoJogadorElement = criarElementoMao(doc, resultado.getMaoJogador());
            jogadorElement.appendChild(maoJogadorElement);

            Element dealerElement = doc.createElement("Dealer");
            resultadoElement.appendChild(dealerElement);

            Element maoDealerElement = criarElementoMao(doc, resultado.getMaoDealer());
            dealerElement.appendChild(maoDealerElement);

            Element vencedorElement = doc.createElement("Vencedor");
            vencedorElement.appendChild(doc.createTextNode(resultado.getVencedor()));
            resultadoElement.appendChild(vencedorElement);

            //fazem se as transformações
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");


            // Remove espaços em branco adicionais

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            // normalize-space() retira os espaços em branco finais, iniciais e consecutivos de uma string
            String expression = "//text()[normalize-space()='']"; //expressão Xpath para encontrar os espaços em branco

            //cria se uma node list com todos os espaços q estão fazios e removem se os os seus "parentes"
            NodeList NodesVazios = (NodeList) xPath.evaluate(expression, doc, XPathConstants.NODESET);
            for (int i = 0; i < NodesVazios.getLength(); i++) {
                Node nodeVazio = NodesVazios.item(i);
                nodeVazio.getParentNode().removeChild(nodeVazio);
            }

            DOMSource source = new DOMSource(doc); //fonte
            StreamResult resultFile = new StreamResult(new File(nomeFicheiro)); //resultado em ficheiro

            transformer.transform(source, resultFile);

            System.out.println("Resultado salvo em resultados.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element criarElementoMao(Document doc, Mao mao) {
        Element maoElement = doc.createElement("Mao");

        //percorre todas as cartas na mão
        for (Carta carta : mao.getMao()) {
            //E criam se os elementos das cartas com os seus valores e naipes
            Element cartaElement = doc.createElement("Carta");

            Element valorElement = doc.createElement("valor");
            valorElement.appendChild(doc.createTextNode(String.valueOf(carta.getValor())));
            cartaElement.appendChild(valorElement);

            Element naipeElement = doc.createElement("naipe");
            naipeElement.appendChild(doc.createTextNode(carta.getNaipe()));
            cartaElement.appendChild(naipeElement);

            maoElement.appendChild(cartaElement);
        }
        //cria se o elemento do valor da mao
        Element valorMaoElement = doc.createElement("valorMao");
        valorMaoElement.appendChild(doc.createTextNode(String.valueOf(mao.getValorMao())));
        maoElement.appendChild(valorMaoElement);

        return maoElement;
    }
}
