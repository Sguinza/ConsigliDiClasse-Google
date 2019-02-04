package consiglidiclasseparser;


import java.io.IOException;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Parser {

    private List<String> lista;

    public Parser() {
        lista = new ArrayList();
    }

    public List parseDocument(String filename)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Document document;
        Element root, element;
        NodeList nodelist;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        document = builder.parse(filename);
        root = document.getDocumentElement();
        lista = new ArrayList();
        String link = "";
        
        // generazione della lista degli elementi "libro"
        nodelist = root.getElementsByTagName("a");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                link = getLink(element);
                lista.add(link);
            }
        }
        return lista;
    }
    
    /*
    private Libro getLibro(Element element) {
        Libro libro;
        String genere = element.getAttribute("genere");
        String titolo = getTextValue(element, "titolo");
        String autore = getTextValue(element, "autore");
        float prezzo = getFloatValue(element, "prezzo");
        libro = new Libro(genere, titolo, autore, prezzo);
        return libro;
    }
    */
    
    private String getLink(Element element)
    {
        return element.getAttribute("href");
    }
    
    /*
    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > 0) {
            value = nodelist.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
    
    // restituisce il valore intero dell’elemento figlio specificato
    private int getIntValue(Element element, String tag) {
        return Integer.parseInt(getTextValue(element, tag));
    }
    
    // restituisce il valore numerico dell’elemento figlio specificato
    private float getFloatValue(Element element, String tag) {
        return Float.parseFloat(getTextValue(element, tag));
    }
    
    */
    
    
}