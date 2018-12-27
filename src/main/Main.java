package main;

import java.io.FileNotFoundException;
import GUI.graphic;
import csvtoxml.Csvtoxml;
import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import relationalCategory.Row;
import relationalCategory.SubtabelCategory;
import relationalCategory.Table;

public class Main {

    public static void main(String args[]) throws ParserConfigurationException, FileNotFoundException {
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
        
        Scanner reader = new Scanner(new File("src\\persons\\Person.csv"));

        String[] attributes = reader.nextLine().split(",");

        Table persons = new Table("Persons", attributes);

        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(",");
            Row record = new Row(attributes, line);
            persons.addRow(record);
        }

        System.out.println(persons.toString());
        SubtabelCategory category = new SubtabelCategory("persons");
        category.addTable(persons);

        //Here we need to choose subtables. (At the moment there is only one.) If we generate all the subtables, there are more than 2^|Persons| choices.
        String[] attributes2 = {"id", "firstname", "lastname"};
        String[] record2 = {"933", "Mahinda", "Perera"};
        Row row1 = new Row(attributes2, record2);
        Table table1 = new Table("person1", attributes2);
        table1.addRow(row1);
        category.addTable(table1);
    }

}
