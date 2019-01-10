package queryProcessing;

import java.io.FileNotFoundException;
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
            String[] attributes1 = {"personId", "productId", "orderDate", "totalPrice", "orderLine"};
            Table smallTreeTable = smallTree.subtreeToTable(attributes1, smallTree.getRoot());
            System.out.println(smallTreeTable.toString());
            smallTree.printGraphicTree();
            System.out.println(" ");
            Node newRoot = smallTree.getRoot().getChildren().get(0).getChildren().get(4);
            String[] attributes2 = {"productId", "asin", "title", "price", "brand"};
            Row smallerTreeRow = smallTree.subtreeToRow(attributes2, newRoot);
            System.out.println(smallerTreeRow.toString());

        } catch (FileNotFoundException | DOMException f) {
            System.out.println("Error: " + f.getMessage());
        }
    }

}
