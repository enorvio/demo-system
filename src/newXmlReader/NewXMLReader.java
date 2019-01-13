package newXmlReader;

import java.io.File;

import javax.swing.text.Document;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NewXMLReader {
	public String filename;
	public DefaultMutableTreeNode tree;
	public relationalCategory.Table invoices;
	public relationalCategory.Table orderlines;
	
	public NewXMLReader(String filename) {
		 try {
	         File inputFile = new File(filename);
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler(new DefaultMutableTreeNode("invoices"));
	         saxParser.parse(inputFile, userhandler); 
	         this.tree = userhandler.getTree();
	         this.invoices = userhandler.getInvoices();
	         this.orderlines = userhandler.getOrderlines();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	public NewXMLReader (String filename, DefaultMutableTreeNode root) {
		try {
	         File inputFile = new File(filename);
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler(root);
	         saxParser.parse(inputFile, userhandler); 
	         this.tree = userhandler.getTree();
	         this.invoices = userhandler.getInvoices();
	         this.orderlines = userhandler.getOrderlines();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}
	
	public DefaultMutableTreeNode getTree() {
		return this.tree;
	}
	
	public relationalCategory.Table getInvoices() {
		return this.invoices;
	}
	
	public relationalCategory.Table getOrderlines() {
		return this.orderlines;
	}
	
	
}
