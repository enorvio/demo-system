package csvtograph;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JComponent;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import GUI.DataFrame;
import dataViewers.graphViewer.GraphViewer;
import graph.DataContainerEdge;
import graph.DataContainerVertex;

public class CsvToGraph {
	public CsvToGraphPreparation preparation;
	public String EdgesFilename;
	public String NodesFilename;
	public ListenableGraph<String, DefaultEdge> graph;
	public ArrayList<DataContainerEdge> rawEdges;
	public ArrayList<DataContainerVertex> defaultVertices;
	public ArrayList<DataContainerVertex> shownVertices;
	public ArrayList<String> labels;
	
	
	public CsvToGraph(String EdgesFilename, String NodesFilename) throws FileNotFoundException {
		this.EdgesFilename = EdgesFilename;
		this.NodesFilename = NodesFilename;
		this.preparation = new CsvToGraphPreparation(this.EdgesFilename, this.NodesFilename);
		this.graph = new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));
		this.rawEdges = preparation.getEdges();
		this.defaultVertices = preparation.getVertices();
		// the last row means that the default is that every vertex shall be displayed. this can be changed by Select
		//Vertices
		this.labels = new ArrayList<String>();
	}
		
	public void SelectVertexAttributeShown(DataContainerVertex vertex, String attribute, boolean showBoth) {
		if (this.defaultVertices.contains(vertex) && vertex.getDataAndAttributes().containsKey(attribute)) {
			String label = vertex.getDataAndAttributes().get(attribute);
			if (showBoth) {
				label = attribute + ": " + label; 
				}
			this.labels.add(attribute + ": " + label);
			this.graph.addVertex(label);
		}
	}
	
	public ArrayList<DataContainerEdge> getEdges() {
		return this.rawEdges;
	}
	
	public void selectAllDefaultLabels() {
		for (DataContainerVertex v : this.defaultVertices) {
			String label = v.getDataAndAttributes().get(v.getNodeType());
			this.labels.add(v.getNodeType() + ": " + label);
			this.graph.addVertex(v.getNodeType() + ": " + label);
		}
	}
	
	public void addConnectionManually(String v1, DataContainerEdge e, String v2) {
		if (this.labels.contains(v1)&&this.labels.contains(v2)&&this.rawEdges.contains(e)) {
			
			this.graph.addEdge(v1, v2, e);
		}
	}
	
	public ListenableGraph<String, DefaultEdge> getFinalGraph() {
		return this.graph;
		}
        	
}
