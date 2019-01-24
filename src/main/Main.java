package main;

import GUI.GraphicsMain;
import java.io.FileNotFoundException;
import csvConverters.CsvToXml;
import newXmlReader.NewXMLReader;
import csvtograph.CsvToGraphPreparation;
import dataViewers.TableViewer;
import graph.Edge;
import java.util.HashSet;

import java.io.File;
import java.util.Scanner;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.ParserConfigurationException;
import relationalCategory.Row;
import relationalCategory.SubtableCategory;
import relationalCategory.Table;
import javax.swing.tree.DefaultTreeModel;
import dataViewers.TreeViewer;
import queryProcessing.SQLquery;

public class Main {

    public static void main(String args[]) throws ParserConfigurationException, FileNotFoundException {
          new GraphicsMain();
//          csvtograph.CsvToGraphPreparation c = new csvtograph.CsvToGraphPreparation("src/graphData/Edge.csv", "src/graphData/Node.csv");
//          c.print();

//        Scanner reader = new Scanner(new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.csv"));
//
//        String[] attributes = reader.nextLine().split(",");
//
//        Table persons = new Table("Persons", attributes);
//        while (reader.hasNextLine()) {
//            String[] line = reader.nextLine().split(",");
//            Row record = new Row(attributes, line);
//            persons.addRow(record);
//        }
//
//        String[] attributes2 = {"id", "firstName", "lastName"};
//        String[] record2 = {"933", "Mahinda", "Perera"};
//        Row row1 = new Row(attributes2, record2);
//        Table table1 = new Table("Mahinda", attributes2);
//        table1.addRow(row1);
//
//        //Another table
//        String[] attributes3 = {"id", "firstName", "lastName", "gender", "birthday", "creationDate"};
//        String[] record3 = {"6597069771578", "Peter", "Taylor", "male", "1983-01-24", "2010-09-06T21:55:34.855+0000"};
//        Row row2 = new Row(attributes3, record3);
//        Table table2 = new Table("Peter", attributes3);
//        table2.addRow(row2);
//
//        //Table that is union of table1 and table2
//        Table table3 = new Table("Mahinda & Peter", attributes2);
//        String[] record4 = {"6597069771578", "Peter", "Taylor"};
//        String[] record5 = {"933", "Mahinda", "Perera"};
//        Row row4 = new Row(attributes2, record4);
//        Row row5 = new Row(attributes2, record5);
//        table3.addRow(row5);
//        table3.addRow(row4);
//
//        //Because persons contains the subtable table1, the return value is 1.
//        //System.out.println(table3.compare(table2));
//        //Construction the subtable category (tree structure) assuming that all the tables are added before construction and they are added in right order.
//        SubtableCategory category = new SubtableCategory("persons");
//        category.addTable(persons);
//        category.addTable(table3);
//        category.addTable(table1);
//        category.addTable(table2);
//
//        DefaultTreeModel tree = category.constructSubtableCategory();
//        TreeViewer relationalTree = new TreeViewer((DefaultMutableTreeNode) tree.getRoot());

//        CsvToXml converter = new CsvToXml("Persons",
//                "Person",
//                new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.csv"),
//                "C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.xml");
//        converter.convertCSVtoXML();

        
        //NewXMLReader newreader = new newXmlReader.NewXMLReader("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\invoices\\invoices1.xml");
        //TreeViewer viewer = new newXmlReader.TreeViewer("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\invoices\\invoices1.xml");
        //TreeViewer viewer1 = new newXmlReader.TreeViewer("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.xml");
        //SQLquery demo = new SQLquery(new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.csv"));
        //String[] attributes2 = {"id", "firstname", "surname"};
        //DefaultMutableTreeNode result = demo.loadDemoQueryTree(attributes2, "933").tabletoTree();
        //TreeViewer viewer2 = new TreeViewer(result);
    }
}
