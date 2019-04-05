package queryButtonsLogic;

import GUI.DataFrame;
import dataViewers.TableViewer;
import dataViewers.TreeViewer;
import dataViewers.graphViewer.GraphViewer;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import queryProcessing.SQLquery;
import relationalCategory.Table;

public class SQLQueryButton {

    public void execute() {
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
            new DataFrame(components, tabHeaders);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
