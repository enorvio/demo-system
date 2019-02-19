package GUI;

import dataViewers.TableViewer;
import csvtograph.CsvToGraph;
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
import graph.DataContainerEdge;
import graph.DataContainerVertex;
import graph.GraphToEdgeVerticeTables;

import java.util.ArrayList;
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
        this.sqlButton = new JButton("Execute SQL query (1)");
        this.xmlButton = new JButton("Execute XPath query (2)");
        this.graphButton = new JButton("Execute Graph query (3)");
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
                SQLquery demo = new SQLquery(new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\demoData\\persons\\Person.csv"));
                Table tableResult = demo.loadDemoQuery(attributes2, "933");
                ListenableGraph<Object, DefaultEdge> lg = tableResult.getListenableGraph();
                GraphViewer graphviewer = new GraphViewer(lg);
                DefaultMutableTreeNode treeResult = tableResult.tabletoTree();
                TableViewer tabelviewer = new TableViewer(tableResult);
                TreeViewer treeviewer = new TreeViewer(treeResult);
                JComponent[] components = {treeviewer.getGraphicTree(), graphviewer.getGraphPanel(), tabelviewer.getGraphicTable()};
                String[] tabHeaders = {"Tree", "Graph", "Table"};
                DataFrame datawindow = new DataFrame(components, tabHeaders);
            } catch (FileNotFoundException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else if (e.getSource() == this.xmlButton) {
            XPathQuery query = new XPathQuery();
            DefaultMutableTreeNode treeResult = query.loadXPathQuery();
            DefaultMutableTreeNode treeResult2 = query.loadXPathQuery();
            TreeToTableFunctor functor = new TreeToTableFunctor();
            TreeViewer treeviewer = new TreeViewer(treeResult2);
            JScrollPane grapichTreeResult = treeviewer.getGraphicTree();
            HashSet<String> primaryKeys = new HashSet<>();
            primaryKeys.add("OrderId");
            Table[] tableResult = functor.runFunctor(treeResult, primaryKeys);
            JComponent[] components = new JComponent[tableResult.length + 1];
            String[] tabHeaders = new String[tableResult.length + 1];
            components[0] = grapichTreeResult;
            tabHeaders[0] = "Tree";
            TreeToGraph treetograph = new TreeToGraph(treeResult2);
            DefaultDirectedGraph graph = treetograph.getDirectedGraph();
            ListenableGraph<Object, DefaultEdge> lg = new DefaultListenableGraph<>(graph);
            GraphViewer graphviewer = new GraphViewer(lg);
            components[1] = graphviewer.getGraphPanel();
            tabHeaders[1] = "Graph";
            for (int i = 2; i < tableResult.length + 1; i++) {
                TableViewer tabelviewer = new TableViewer(tableResult[i - 2]);
                components[i] = tabelviewer.getGraphicTable();
                tabHeaders[i] = "Table";
            }
            DataFrame datawindow = new DataFrame(components, tabHeaders);
        } else if (e.getSource() == this.graphButton) {
            try {
                CsvToGraph ctg = new CsvToGraph("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\demoData\\graphData\\Edge.csv",
                        "C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\demoData\\graphData\\Node.csv");
                ArrayList<DataContainerEdge> edgelist = ctg.getEdges();
                ArrayList<DataContainerVertex> nodelist = ctg.getVertices();
                System.out.println(edgelist.get(1));
                ctg.addConnectionManually(nodelist.get(0), edgelist.get(0), nodelist.get(1));
                ctg.addConnectionManually(nodelist.get(0), edgelist.get(1), nodelist.get(2));
                ListenableGraph<Object, DefaultEdge> lg = ctg.getListenableGraph();
                GraphViewer graphviewer = new GraphViewer(lg);

                DefaultDirectedGraph graph = ctg.getGraph();
                GraphToEdgeVerticeTables graphTables = new GraphToEdgeVerticeTables(graph);
                Table[] verticeTables = graphTables.getVerticeTables();
                Table[] edgeTables = graphTables.getEdgeTables();
                JComponent[] components = new JComponent[verticeTables.length + edgeTables.length + 1];
                components[0] = graphviewer.getGraphPanel();
                String[] tabHeaders = new String[verticeTables.length + edgeTables.length + 1];
                tabHeaders[0] = "Graph";
                for (int i = 1; i < verticeTables.length + 1; i++) {
                    TableViewer tabelviewer = new TableViewer(verticeTables[i - 1]);
                    components[i] = tabelviewer.getGraphicTable();
                    tabHeaders[i] = "Vertice Table";
                }
                
                for (int i = 0; i < edgeTables.length; i++) {
                    TableViewer tabelviewer = new TableViewer(edgeTables[i]);
                    components[verticeTables.length + 1 + i] = tabelviewer.getGraphicTable();
                    tabHeaders[verticeTables.length + 1 + i] = "Edge Table";
                }

                DataFrame datawindow = new DataFrame(components, tabHeaders);

            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
