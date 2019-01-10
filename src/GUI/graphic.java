package GUI;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.awt.*;
import javax.swing.*;

import org.w3c.dom.DOMException;

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {

            System.out.println("Not supported yet");
        } else if (e.getSource() == b2) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Select a query:");
            System.out.println("(1) An XPath query: .//invoice[personId=�10995116278711�]/orderline");
            System.out.println("(2) SQL query:  Select FirstName, LastName from Person where id = �933�");
            System.out.println("(E) Exit");
            while (true) {
                String input = scan.nextLine();
                if (input.equals("1")) {
                    XPathQuery path = new XPathQuery();
                    path.loadXPathQuery();
                } else if (input.equals("2")) {
                    try {
                        SQLquery demo = new SQLquery();
                        demo.loadDemoQueryTree().printGraphicTree();
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                } else if (input.equals("E")) {
                    break;
                } else {
                    System.out.println("Please type 1, 2 or E");
                }
            }
            System.exit(0);
        }
    }

}
