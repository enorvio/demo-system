package GUI;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import java.util.Scanner;
import queryProcessing.SQLquery;
import queryProcessing.XPathQuery;
import xmlReader.*;
import tree.*;
import relationalCategory.*;

public class graphic extends JFrame implements ActionListener {

    JButton b1, b2;

    public graphic() {
        super("Category theory on multi-model databases");

        b1 = new JButton("Load data");
        b1.setBounds(10, 90, 600, 80);
        b1.setFont(new Font("Arial", Font.PLAIN, 40));
        b2 = new JButton("Query processing");
        b2.setBounds(620, 90, 600, 80);
        b2.setFont(new Font("Arial", Font.PLAIN, 40));

        JLabel l1 = new JLabel("Category theory on multi-model databases");
        l1.setFont(new Font("Arial", Font.PLAIN, 40));
        add(b1);
        add(b2);
        add(l1);
        l1.setBounds(10, 10, 1200, 60);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setLayout(null);
        setSize(1250, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            System.out.println("Not supported yet");
        } else if (e.getSource() == b2) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Select a query:");
            System.out.println("(1) An XPath query: .//invoice[personId=�10995116278711�]/orderline");
            System.out.println("(2) SQL query:  Select FirstName, LastName from Person where id = �933�");
            System.out.println("(E) Exit");
            OUTER:
            while (true) {
                String input = scan.nextLine();
                switch (input) {
                    case "1":
					XPathQuery path = null;
					try {
						path = new XPathQuery("src/invoices/invoices1.xml", "/Invoices/Invoice.xml/Orderline/price");
					} catch (XPathExpressionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParserConfigurationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SAXException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                        break;
                    case "2":
                        try {
                            String[] attributes2 = {"id", "name", "surname"};
                            SQLquery demo = new SQLquery(new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.csv"));
                            System.out.println("Result to the query in tree format: ");
                            Tree result = demo.loadDemoQueryTree(attributes2, "933").tabletoTree();
                            result.printGraphicTree();
                            System.out.println("");
                            System.out.println("Result to the query in table format: ");
                            result.subtreeToTable(attributes2, result.getRoot()).printGraphicTable();
                        } catch (FileNotFoundException ex) {
                            System.out.println("Error: " + ex.getMessage());
                        }   break;
                    case "E":
                        break OUTER;
                    default:
                        System.out.println("Please type 1, 2 or E");
                        break;
                }
            }
            System.exit(0);
        }
    }

}
