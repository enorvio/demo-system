package graph;
import java.util.ArrayList;

public class Graph {

	private ArrayList<Vertex> vertices;
	
	public Graph() {
		this.vertices = new ArrayList<Vertex>();
	}
	
	public ArrayList<Vertex> getVertices() {
		return this.vertices;
	}
	
	public void addVertex(Vertex v) {
		this.vertices.add(v);
	}
	
	public void addEdge(Vertex start, Vertex end, Object weight) {
		Edge e = new Edge(start, end, weight);
		start.addEdgeOut(e);
		end.addEdgeIn(e);
	}
	
	public void removeEdge() {
		//todo
	}
	
	public void removeVertex() {
		//todo
	}
	
}
