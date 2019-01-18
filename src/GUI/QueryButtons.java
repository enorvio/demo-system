package GUI;

import dataViewers.TableViewer;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import dataViewers.TreeViewer;
import javax.swing.JTabbedPane;
import queryProcessing.SQLquery;
import queryProcessing.XPathQuery;
import relationalCategory.Table;

public class QueryButtons extends JComponent implements ActionListener {

    private final JButton sqlButton;
    private final JButton xmlButton;
    private final JButton graphButton;

    public QueryButtons() {
        this.sqlButton = new JButton("Execute SQL query: Select FirstName, LastName from Person where id = \"933\"");
        this.xmlButton = new JButton("Execute XPath query: .//invoice[personId=\"10995116278711\"]/orderline");
        this.graphButton = new JButton("Execute Graph query: ");
        setLayout(new FlowLayout());
        this.sqlButton.setFont(new Font("Arial", Font.PLAIN, 18));
        this.xmlButton.setFont(new Font("Arial", Font.PLAIN, 18));
        this.graphButton.setFont(new Font("Arial", Font.PLAIN, 18));

        add(this.sqlButton);
        add(this.xmlButton);
        add(this.graphButton);

        this.sqlButton.addActionListener(this);
        this.xmlButton.addActionListener(this);
        this.graphButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.sqlButton) {
            try {
                String[] attributes2 = {"id", "firstname", "surname"};
                SQLquery demo = new SQLquery(new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.csv"));
                Table tableResult = demo.loadDemoQueryTree(attributes2, "933");
                DefaultMutableTreeNode treeResult = tableResult.tabletoTree();
                TableViewer tabelviewer = new TableViewer(tableResult);
                TreeViewer treeviewer = new TreeViewer(treeResult);
                JComponent[] components = {tabelviewer.getGraphicTable(), treeviewer.getGraphicTree()};
                DataFrame datawindow = new DataFrame(components);
            } catch (FileNotFoundException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else if (e.getSource() == this.xmlButton) {
            XPathQuery query = new XPathQuery();
            query.loadXPathQuery();
        } else if (e.getSource() == this.xmlButton) {
            System.out.println("Not supported yet.");
        }
    }

}
