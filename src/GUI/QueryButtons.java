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
import dataViewers.graphViewer.GraphViewer;
import graph.DataContainerEdge;
import graph.DataContainerVertex;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JScrollPane;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import queryProcessing.SQLquery;
import queryProcessing.XPathQuery;
import relationalCategory.Table;
import tree.TreeFunctor;

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
//            ListenableGraph<String, DefaultEdge> g
//                    = new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));
            
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("name", "Jeffery");
            DataContainerVertex v1 = new DataContainerVertex("Jeffery", map1);
            
            HashMap<String, String> map2 = new HashMap<>();
            map2.put("name", "University of Helsinki");
            DataContainerVertex v2 = new DataContainerVertex("HelsinkiUniversity", map2);
            
            HashMap<String, String> map3 = new HashMap<>();
            map3.put("name", "Stockholm University");
            DataContainerVertex v3 = new DataContainerVertex("StockholmUniversity", map3);
            
            HashMap<String, String> map4 = new HashMap<>();
            map4.put("since", "2018");
            DataContainerEdge e1 = new DataContainerEdge("WORKS_FOR", map4);
            
            HashMap<String, String> map5 = new HashMap<>();
            map5.put("year", "2003");
            DataContainerEdge e2 = new DataContainerEdge("GRADUATED", map5);
            
            DefaultDirectedGraph graph = new DefaultDirectedGraph<>(DefaultEdge.class);
            graph.addVertex(v1);
            graph.addVertex(v2);
            graph.addVertex(v3);
            graph.addEdge(v1, v2, e1);
            graph.addEdge(v1, v3, e2);
            
            ListenableGraph<Object, DefaultEdge> g = new DefaultListenableGraph<>(graph);
            
            GraphViewer graphviewer = new GraphViewer(g);
            JComponent[] components = {graphviewer.getGraphPanel()};
            DataFrame datawindow = new DataFrame(components);
        }
    }

}
