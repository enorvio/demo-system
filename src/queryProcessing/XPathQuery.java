package queryProcessing;

import java.io.FileNotFoundException;
import javax.swing.tree.DefaultMutableTreeNode;
import newXmlReader.NewXMLReader;
import org.w3c.dom.DOMException;

import relationalCategory.Row;
import relationalCategory.Table;

public class XPathQuery {

    public DefaultMutableTreeNode loadXPathQuery() {
            NewXMLReader reader = new NewXMLReader("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\invoices\\invoices3.xml");
            DefaultMutableTreeNode smallTree = reader.getTree();
            return smallTree;
//            smallTree.printGraphicTree();
//            System.out.println("");
//            String[] attributes1 = {"personId", "productId", "orderDate", "totalPrice", "orderLine"};
//            Table smallTreeTable = smallTree.subtreeToTable(attributes1, smallTree.getRoot());
//            smallTreeTable.printGraphicTable();
//            System.out.println(smallTreeTable.toString());
//            Node newRoot = smallTree.getRoot().getChildren().get(0).getChildren().get(4);
//            String[] attributes2 = {"productId", "asin", "title", "price", "brand"};
//            Row smallerTreeRow = smallTree.subtreeToRow(attributes2, newRoot);
//            Table table = new Table("table", attributes2);
//            table.addRow(smallerTreeRow);
//            System.out.println(smallerTreeRow.toString());
//            table.printGraphicTable();
    }
}
