package tree;
import java.util.ArrayList;
import graph.*;
import relationalCategory.*;

public class SubtreeCategory {

	private Graph categoryGraph;
	private Tree supertree;
	private Vertex max;
	
	public SubtreeCategory(Tree supertree) {
		this.supertree = supertree;
		this.categoryGraph = new Graph();
		this.max = new Vertex("supertree", supertree);
	}
	
	public void addSubtree(Tree subtree, String name) {
		graph.Vertex v = new Vertex(name, subtree);
		this.categoryGraph.addVertex(v);
		this.categoryGraph.addEdge(v, max, name);
	}
	
	public void addRelation(Vertex x, Vertex y, Object weight) {
		if (this.categoryGraph.getVertices().contains(x) && this.categoryGraph.getVertices().contains(y)) {
			this.categoryGraph.addEdge(x, y, weight);
		}
	}
	
			
}

	
