package graph;

import relationalCategory.Row;

public class Vertex {
	
public String label;
public Row vertexRow;

public Vertex(String defaultLabel, String[] attributes, String[] values) {
	this.vertexRow = new Row(attributes, values);
	this.label = defaultLabel;
}

public void setLabel(String newLabel) {
	this.label = newLabel;
}
}

	
