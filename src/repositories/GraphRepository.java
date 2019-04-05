package backend.repositories;

import org.jgrapht.graph.DefaultDirectedGraph;

import graphCategory.DataContainerVertex;

public class GraphRepository {

	private int id;
	private String name;
	private DataContainerVertex[] nodes;
	private Edge[] links;

	public GraphRepository(String name, int id, DefaultDirectedGraph<?, Object> g) {
		this.name = name;
		this.id = id;
		
		this.links = new Edge[g.edgeSet().size()];
		int i = 0;
		for(Object edge : g.edgeSet()) {
			DataContainerVertex source = (DataContainerVertex) g.getEdgeSource(edge);
			DataContainerVertex target = (DataContainerVertex) g.getEdgeTarget(edge);
			Edge newEdge = new Edge(source, target);
			this.links[i] = newEdge;
			i++;
		}
		
		this.nodes = new DataContainerVertex[g.vertexSet().size()];
		int j = 0;
		for(Object vertex : g.vertexSet()) {
			this.nodes[j] = (DataContainerVertex) vertex;
			j++;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataContainerVertex[] getNodes() {
		return nodes;
	}

	public void setNodes(DataContainerVertex[] nodes) {
		this.nodes = nodes;
	}

	public Edge[] getLinks() {
		return links;
	}

	public void setLinks(Edge[] edges) {
		this.links = edges;
	}
}
