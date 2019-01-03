package graph;
import java.util.ArrayList;

public class Vertex {

	private String name;
	private ArrayList<Edge> edgesIn;
	private ArrayList<Edge> edgesOut;
	private Object data;
	
	public Vertex(String name, Object data) {
		this.name = name;
		this.edgesIn = new ArrayList<Edge>();
		this.edgesOut = new ArrayList<Edge>();
		this.data = data;
	}
	
	public void addEdgeIn(Edge e) {
		if (e.getEnd().equals(this)) {
			this.edgesIn.add(e);
		}
		else {
			System.out.println("End of the edge does not match.");
		}
	}
	
	public void addEdgeOut(Edge e) {
		if (e.getStart().equals(this)) {
			this.edgesOut.add(e);
		}
		else {
			System.out.println("Start of the edge does not match.");
		}
	}
	
	public void removeEdgeIn(Edge e) {
		this.edgesIn.remove(e);
		this.edgesOut.remove(e);
	}
	
	public ArrayList<Edge> getEdgesIn(){
		return this.edgesIn;
	}
	
	public ArrayList<Edge> getEdgesOut(){
		return this.edgesOut;
	}
	
	
}
