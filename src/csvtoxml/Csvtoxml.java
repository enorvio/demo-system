/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvtoxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Valter
 */
public class Csvtoxml {

    private String root;
    private String childroot;
    private File csvfile;
    private File xmlfile;

    public Csvtoxml(String rootname, String childrootname, File csvfilename, String xmlfilename) {
        this.root = rootname;
        this.childroot = childrootname;
        this.csvfile = csvfilename;
        this.xmlfile = new File(xmlfilename);
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws ParserConfigurationException
     */
    public void convertCSVtoXML() throws FileNotFoundException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        try {
            Scanner reader = new Scanner(this.csvfile);
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(this.root);
            doc.appendChild(rootElement);
            //Assuming the first row contains DTD
            String[] schema = reader.nextLine().split(",");

            while (reader.hasNextLine()) {         	
                String[] info = reader.nextLine().split(",");

                Element person = doc.createElement(this.childroot);
                rootElement.appendChild(person);

                for (int i = 0; i < schema.length; i++) {
                    Element xmlelement = doc.createElement(schema[i]);
                    xmlelement.appendChild(doc.createTextNode(info[i]));
                    person.appendChild(xmlelement);
                }

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(this.xmlfile);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (TransformerException pce) {
        } catch (FileNotFoundException | DOMException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
