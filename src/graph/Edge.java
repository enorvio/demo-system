package graph;

import relationalCategory.Row;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {
	
	public Row edgeRow;
	
	public Edge(String[] attributes, String[] values) {
		this.edgeRow = new Row(attributes, values);	
	}
	
	public Row getRow() {
		return this.edgeRow;
	}
}
