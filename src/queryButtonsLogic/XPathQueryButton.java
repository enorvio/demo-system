package queryButtonsLogic;

import GUI.DataFrame;
import dataViewers.TableViewer;
import dataViewers.TreeViewer;
import dataViewers.graphViewer.GraphViewer;
import java.util.HashSet;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import queryProcessing.XPathQuery;
import relationalCategory.Table;
import treeCategory.TreeToGraph;
import treeCategory.TreeToTableFunctor;

public class XPathQueryButton {

    public void execute() {
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
    }
}
