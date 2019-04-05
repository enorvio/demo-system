package backend.repositories;

import graphCategory.DataContainerVertex;

public class Edge {
	
	private int source;
	private int target;
	
	public Edge(DataContainerVertex source, DataContainerVertex target) {
		this.source = source.getId();
		this.target = target.getId();
	}
	
	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

}
