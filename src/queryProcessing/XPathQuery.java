package queryProcessing;

import javax.swing.tree.DefaultMutableTreeNode;
import newXmlReader.NewXMLReader;

public class XPathQuery {

    public DefaultMutableTreeNode loadXPathQuery() {
            NewXMLReader reader = new NewXMLReader("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\demoData\\invoices\\invoices2.xml");
            DefaultMutableTreeNode smallTree = reader.getTree();
            return smallTree;
    }
}
