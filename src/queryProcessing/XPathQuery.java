package queryProcessing;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;

import relationalCategory.Row;
import relationalCategory.Table;
import tree.Node;
import tree.Tree;

public class XPathQuery {

	public void loadXPathQuery() {
		        try {
		            xmlReader.Reader reader = new xmlReader.Reader("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\invoices\\invoices3.xml");
		            Tree smallTree = reader.createTree("Invoices");
		            smallTree.printGraphicTree();
		            System.out.println("");
		            String[] attributes1 = {"personId", "productId", "orderDate", "totalPrice", "orderLine"};
		            Table smallTreeTable = smallTree.subtreeToTable(attributes1, smallTree.getRoot());
		            smallTreeTable.printGraphicTable();
		            //System.out.println(smallTreeTable.toString());
		            System.out.println(" ");
		            Node newRoot = smallTree.getRoot().getChildren().get(0).getChildren().get(4);
		            String[] attributes2 = {"productId", "asin", "title", "price", "brand"};
		            Row smallerTreeRow = smallTree.subtreeToRow(attributes2, newRoot);
		            Table table = new Table("table", attributes2);
		            table.addRow(smallerTreeRow);
		            //System.out.println(smallerTreeRow.toString());
		            table.printGraphicTable();

		        } catch (FileNotFoundException | DOMException f) {
		            System.out.println("Error: " + f.getMessage());
		        }
		    }
    
}
