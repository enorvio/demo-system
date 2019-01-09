package main;
import tree.*;

import java.io.FileNotFoundException;
import GUI.graphic;
import csvtoxml.Csvtoxml;
import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import relationalCategory.Row;
import relationalCategory.SubtabelCategory;
import relationalCategory.Table;
import tree.Tree;

public class Main {

    public static void main(String args[]) throws ParserConfigurationException, FileNotFoundException {
        new graphic();
        try {
            //Csvtoxml converter = new Csvtoxml("Persons", "Person", new File("src/persons/Person.csv"), "Person.xml");
            //converter.convertCSVtoXML();
            xmlReader.Reader reader1 = new xmlReader.Reader("src/invoices/invoices1.xml");
            tree.Tree t = reader1.createTree("Invoices1");
            xmlReader.Reader reader2 = new xmlReader.Reader("src/invoices/invoices2.xml");
            tree.Tree t1 = reader2.createTree("Invoices2");
            String[] attributes = {"OrderId", "PersonId", "OrderDate", "TotalPrice"};
            Table invoices = t.subtreeToTable(attributes, t.getRoot());
            System.out.println(invoices.toString());
           

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        String[] attributes = reader.nextLine().split(",");

        Table persons = new Table("Persons", attributes);
        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(",");
            Row record = new Row(attributes, line);
            persons.addRow(record);
        }
        //We can print the table.Many lines.
        //System.out.println(persons.toString());
        
        //Here we need to choose subtables. If we generate all the subtables, there are more than 2^|Persons| choices.
        String[] attributes2 = {"id", "firstName", "lastName"};
        String[] record2 = {"933", "Mahinda", "Perera"};
        Row row1 = new Row(attributes2, record2);
        Table table1 = new Table("Mahinda", attributes2);
        table1.addRow(row1);
        
        //Another table
        String[] attributes3 = {"id", "firstName", "lastName", "gender", "birthday", "creationDate"};
        String[] record3 = {"6597069771578","Peter","Taylor","male","1983-01-24","2010-09-06T21:55:34.855+0000"};
        Row row2 = new Row(attributes3, record3);
        Table table2 = new Table("Peter", attributes3);
        table2.addRow(row2);
        
        //Table that is union of table1 and table2
        Table table3 = new Table("Mahinda & Peter", attributes2);
        String[] record4 = {"6597069771578","Peter","Taylor"};
        String[] record5 = {"933", "Mahinda", "Perera"};
        Row row4 = new Row(attributes2, record4);
        Row row5 = new Row(attributes2, record5);
        table3.addRow(row5);
        table3.addRow(row4);
        
        //Because persons contains the subtable table1, the return value is 1.
        //System.out.println(table3.compare(table2));
        
        //Construction the subtable category (tree structure) assuming that all the tables are added before construction and they are added in right order.
        SubtabelCategory category = new SubtabelCategory("persons");
        category.addTable(persons);
        category.addTable(table3);
        category.addTable(table1);
        category.addTable(table2);
        Tree tree = category.constructSubtableCategory();
        //System.out.println(tree.getRoot().getChildren().size());
        //tree.printTree();
        tree.printGraphicTree();
    }

}
