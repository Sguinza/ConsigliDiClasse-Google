/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package consiglidiclasseparser;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author guindani_christian
 */
public class ConsigliDiClasseParser {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String percorsoXml = "ConsigliClasse.xml";
        String percorsoCsv = "Consigli.csv";
        ReadWriteLib rw;
        List lista = null;
        Parser dom = new Parser();
        try {
            rw = new ReadWriteLib(percorsoXml, percorsoCsv);
            lista = dom.parseDocument(percorsoXml);
            for(int i = 0; i < lista.size(); i++)
            {
                rw.writeFile(lista.toString());
            }
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            System.out.println("Errore!");
        }    }
    
}
