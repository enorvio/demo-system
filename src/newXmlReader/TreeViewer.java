package newXmlReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Event.*;
import java.io.*;
import javax.swing.tree.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class TreeViewer {
	private NewXMLReader reader;
	private String filename;
	private JFrame frame;
	
	public TreeViewer(String filename) {
		this.filename = filename;
		this.reader = new NewXMLReader(filename);
		this.frame = new JFrame("Tree View:" + filename);
        frame.setSize(800,800);
        
        frame.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent ev){
                 System.exit(0);
             }
        });
        frame.getContentPane().setLayout(new BorderLayout());  
        JTree tree = new JTree(this.reader.getTree());
        JScrollPane scrollPane = new JScrollPane(tree);
        
        frame.getContentPane().add("Center",scrollPane);                                           
        frame.setVisible(true); 
	}
}
