package GUI;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.awt.*;
import javax.swing.*;
import xmlReader.*;

public class graphic extends JFrame implements ActionListener {
	JButton b1, b2;
	public graphic(){
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
		l1.setBounds(10,10,1200,60);
		b1.addActionListener(this);
		b2.addActionListener(this);
		setLayout(null);
		setSize(1250,250);
		setVisible(true);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			}  
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			System.out.println("Load");
		}
		else if (e.getSource()==b2) {
			System.out.println("Query");
		}
		}	
	}

