package queryProcessing;

import javax.swing.tree.DefaultMutableTreeNode;
import xmlReader.XMLReader;

public class XPathQuery {

    public DefaultMutableTreeNode loadXPathQuery() {
            XMLReader reader = new XMLReader("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\demoData\\invoices\\invoices2.xml");
            DefaultMutableTreeNode smallTree = reader.getTree();
            return smallTree;
    }
}
