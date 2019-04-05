package queryButtonsLogic;

import GUI.DataFrame;
import csvConverters.csvToGraph.CsvToGraph;
import dataViewers.TableViewer;
import dataViewers.TreeViewer;
import dataViewers.graphViewer.GraphViewer;
import graphCategory.DataContainerEdge;
import graphCategory.DataContainerVertex;
import graphCategory.GraphToEdgeVerticeTables;
import graphCategory.GraphToTree;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JComponent;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import relationalCategory.Table;

public class GraphQueryButton {

	private CsvToGraph ctg;
	private ArrayList<DataContainerEdge> edgelist;
	private ArrayList<DataContainerVertex> nodelist;
	

	public GraphQueryButton() throws FileNotFoundException {
		this.ctg = new CsvToGraph("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\demoData\\graphData\\Edge.csv",
				"C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\demoData\\graphData\\Node.csv");
		this.edgelist = this.ctg.getEdges();
		this.nodelist = this.ctg.getVertices();
		this.ctg.addConnectionManually(nodelist.get(0), edgelist.get(0), nodelist.get(1));
		this.ctg.addConnectionManually(nodelist.get(0), edgelist.get(1), nodelist.get(2));
	}
	
	public DefaultDirectedGraph getDataGraph() {
		return this.ctg.getGraph();
	}
	
	public ArrayList<DataContainerEdge> getDataEdges() {
		return this.edgelist;
	}
	
	public ArrayList<DataContainerVertex> getDataVertices() {
		return this.nodelist;
	}

	public void execute() {
		ListenableGraph<Object, DefaultEdge> lg = this.ctg.getListenableGraph();
		GraphViewer graphviewer = new GraphViewer(lg);

		DefaultDirectedGraph graph = this.ctg.getGraph();
		GraphToEdgeVerticeTables graphTables = new GraphToEdgeVerticeTables(graph);
		Table[] verticeTables = graphTables.getVerticeTables();
		Table[] edgeTables = graphTables.getEdgeTables();
		JComponent[] components = new JComponent[verticeTables.length + edgeTables.length + 2];
		components[0] = graphviewer.getGraphPanel();
		String[] tabHeaders = new String[verticeTables.length + edgeTables.length + 2];
		tabHeaders[0] = "Graph";
		tabHeaders[1] = "Tree";
		for (int i = 2; i < verticeTables.length + 2; i++) {
			TableViewer tabelviewer = new TableViewer(verticeTables[i - 2]);
			components[i] = tabelviewer.getGraphicTable();
			tabHeaders[i] = "Vertice table";
		}

		for (int i = 0; i < edgeTables.length; i++) {
			TableViewer tabelviewer = new TableViewer(edgeTables[i]);
			components[verticeTables.length + 2 + i] = tabelviewer.getGraphicTable();
			tabHeaders[verticeTables.length + 2 + i] = "Edge table";
		}

		GraphToTree graphToTree = new GraphToTree(graph);

		TreeViewer treeviewer = new TreeViewer(graphToTree.getTree());
		components[1] = treeviewer.getGraphicTree();

		new DataFrame(components, tabHeaders);
	}
}
