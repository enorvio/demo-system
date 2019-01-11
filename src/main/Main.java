package main;

import java.io.FileNotFoundException;
import GUI.graphic;
import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import relationalCategory.Row;
import relationalCategory.SubtableCategory;
import relationalCategory.Table;
import tree.Tree;

public class Main {

    public static void main(String args[]) throws ParserConfigurationException, FileNotFoundException {

        new graphic();

        Scanner reader = new Scanner(new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.csv"));

        String[] attributes = reader.nextLine().split(",");

        Table persons = new Table("Persons", attributes);
        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(",");
            Row record = new Row(attributes, line);
            persons.addRow(record);
        }

        String[] attributes2 = {"id", "firstName", "lastName"};
        String[] record2 = {"933", "Mahinda", "Perera"};
        Row row1 = new Row(attributes2, record2);
        Table table1 = new Table("Mahinda", attributes2);
        table1.addRow(row1);

        //Another table
        String[] attributes3 = {"id", "firstName", "lastName", "gender", "birthday", "creationDate"};
        String[] record3 = {"6597069771578", "Peter", "Taylor", "male", "1983-01-24", "2010-09-06T21:55:34.855+0000"};
        Row row2 = new Row(attributes3, record3);
        Table table2 = new Table("Peter", attributes3);
        table2.addRow(row2);

        //Table that is union of table1 and table2
        Table table3 = new Table("Mahinda & Peter", attributes2);
        String[] record4 = {"6597069771578", "Peter", "Taylor"};
        String[] record5 = {"933", "Mahinda", "Perera"};
        Row row4 = new Row(attributes2, record4);
        Row row5 = new Row(attributes2, record5);
        table3.addRow(row5);
        table3.addRow(row4);

        //Because persons contains the subtable table1, the return value is 1.
        //System.out.println(table3.compare(table2));
        
        //Construction the subtable category (tree structure) assuming that all the tables are added before construction and they are added in right order.
        SubtableCategory category = new SubtableCategory("persons");
        category.addTable(persons);
        category.addTable(table3);
        category.addTable(table1);
        category.addTable(table2);
        Tree tree = category.constructSubtableCategory();
        //System.out.println(tree.getRoot().getChildren().size());
        //tree.printTree();
        //tree.printGraphicTree();
    }

}
