package graph;

public class Edge {
	private Vertex start;
	private Vertex end;
	private Object weight;
	
	public Edge(Vertex start, Vertex end, Object weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	public Vertex getStart() {
		return this.start;
	}
	
	public Vertex getEnd() {
		return this.end;
	}
}
