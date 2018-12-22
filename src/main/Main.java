package main;

import java.io.FileNotFoundException;
import GUI.graphic;
import csvtoxml.Csvtoxml;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;

public class Main {

    public static void main(String args[]) throws ParserConfigurationException {
        new graphic();
        try {
            Csvtoxml converter = new Csvtoxml("Persons", "Person", new File("src\\persons\\Person.csv"), "Person.xml");
            converter.convertCSVtoXML();
            xmlReader.Reader reader = new xmlReader.Reader("src\\invoices\\invoices1.xml");
            reader.createTree("Invoices");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
