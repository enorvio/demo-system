package GUI;

import dataViewers.TableViewer;
import csvtograph.CsvToGraphPreparation;
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
import dataViewers.graphViewer.GraphViewer;
import java.util.HashSet;
import javax.swing.JScrollPane;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import queryProcessing.SQLquery;
import queryProcessing.XPathQuery;
import relationalCategory.Table;
import tree.*;

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
                Table tableResult = demo.loadDemoQuery(attributes2, "933");
                DefaultMutableTreeNode treeResult = tableResult.tabletoTree();
                TableViewer tabelviewer = new TableViewer(tableResult);
                TreeViewer treeviewer = new TreeViewer(treeResult);
                JComponent[] components = {treeviewer.getGraphicTree(), tabelviewer.getGraphicTable()};
                DataFrame datawindow = new DataFrame(components);
            } catch (FileNotFoundException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else if (e.getSource() == this.xmlButton) {
            XPathQuery query = new XPathQuery();
            DefaultMutableTreeNode treeResult = query.loadXPathQuery();
            DefaultMutableTreeNode treeResult2 = query.loadXPathQuery();
            TreeFunctor functor = new TreeFunctor();
            TreeViewer treeviewer = new TreeViewer(treeResult2);
            JScrollPane grapichTreeResult = treeviewer.getGraphicTree();
            HashSet<String> primaryKeys = new HashSet<>();
            primaryKeys.add("OrderId");
            Table[] tableResult = functor.runFunctor(treeResult, primaryKeys);
            JComponent[] components = new JComponent[tableResult.length + 1];
            components[0] = grapichTreeResult;
            for (int i = 1; i <= tableResult.length; i++) {
                TableViewer tabelviewer = new TableViewer(tableResult[i - 1]);
                components[i] = tabelviewer.getGraphicTable();
            }
            DataFrame datawindow = new DataFrame(components);
        } else if (e.getSource() == this.graphButton) {
            ListenableGraph<String, DefaultEdge> g
                    = new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));

            String v1 = "v1";
            String v2 = "v2";
            String v3 = "v3";
            String v4 = "v4";

            // add some sample data (graph manipulated via JGraphX)
            g.addVertex(v1);
            g.addVertex(v2);
            g.addVertex(v3);
            g.addVertex(v4);

            g.addEdge(v1, v2);
            g.addEdge(v2, v3);
            g.addEdge(v3, v1);
            g.addEdge(v4, v3);
            
            GraphViewer graphviewer = new GraphViewer(g);
            JComponent[] components = {graphviewer.getGraphPanel()};
            DataFrame datawindow = new DataFrame(components);
        }
    }

}
