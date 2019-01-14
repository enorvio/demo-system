package newXmlReader;

import java.io.File;
import java.io.IOException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReader {

    private File file;
    private DefaultTreeModel tree;
    private DefaultMutableTreeNode root;

    public DOMReader(File fileBeginning) {
        this.file = fileBeginning;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.file);
            doc.getDocumentElement().normalize();
            System.out.println("Created root element :" + doc.getDocumentElement().getNodeName());
            
            this.root = new DefaultMutableTreeNode(doc.getDocumentElement().getNodeName());
            this.tree = new DefaultTreeModel(this.root);
            
            NodeList nList = doc.getChildNodes();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element added to the root :" + nNode.getNodeName());
                DefaultMutableTreeNode newTreeNode = new DefaultMutableTreeNode(nNode.getNodeName());
                this.root.add(newTreeNode);
                walkTree(nNode, newTreeNode);
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void walkTree(Node node, DefaultMutableTreeNode parentNode) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            if (node.getChildNodes().getLength() == 1) {
                //System.out.println("following data saved as String: " + eElement.getNodeName() + " " + eElement.getTextContent());
                DefaultMutableTreeNode newTreeNode = new DefaultMutableTreeNode(eElement.getNodeName() + " " + eElement.getTextContent());
                parentNode.add(newTreeNode);
            }
        }
        if (node.hasChildNodes()) {
            NodeList nList = node.getChildNodes();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node currentNode = nList.item(temp);
                DefaultMutableTreeNode newTreeNode = new DefaultMutableTreeNode(currentNode.getNodeName());
                parentNode.add(newTreeNode);
                walkTree(currentNode, newTreeNode);
            }
        }
    }
    
    public DefaultTreeModel getDOMtree(){
        return this.tree;
    }
}
