package tree;

import java.util.ArrayList;

public class Node {
	private String[] data;
	private Node parent;
	private ArrayList<Node> children;
	
	public Node(Node parent, String[] data) {
		this.parent = parent;
		this.data = data;
		this.children = new ArrayList<Node>();
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public String[] getData() {
		return this.data;
	}
	
	public void addChild(Node child) {
		this.children.add(child);
	}
	
	public String toString() {
		String s = this.data[0] + " " + this.data[1] + " " + this.children.size();
		if (this.parent != null) {
			return this.parent.toString() + " " + s;
		}
		return s;
	}
	
	public void printNode() {
		System.out.println(this.toString());
		for (Node c : this.children) {
			c.printNode();
		}
	}
}
