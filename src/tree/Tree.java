package tree;

import java.util.ArrayList;
import relationalCategory.*;

public class Tree {

    private Node root;
<<<<<<< HEAD
    
    public Tree(Object rootData) {
        this.root = new Node(null, rootData);
    }

    public Tree(Node node) {
        node.setParent(null);
        this.root = node;
    }

    public Node getRoot() {
        return this.root;
    }

=======

    public Tree(Object rootData) {
        this.root = new Node(null, rootData);
    }

    public Tree(Node node) {
        node.setParent(null);
        this.root = node;
    }

    public Node getRoot() {
        return this.root;
    }

>>>>>>> cf3af1e02443c454064e787fc211f4e9acdf9cf8
    public void printTree() {
        this.root.printNode();
    }
   
    public void printGraphicTree() {
        this.root.printGraphicNode();
    }
<<<<<<< HEAD
        
    public Row subtreeToRow(String[] attributes, Node n) {
    	int l = attributes.length;
    	String[] row = new String[l];
    	for (int i=0; i<l; i++) {
    		if (!n.getChildren().isEmpty()) {
    			String[] value = (String[]) n.getChildren().get(i).getData();
        		row[i] = value[1];
    		}   		
    	}
    	Row record = new Row(attributes, row);
    	return record;
    	}
    
    
	public Table subtreeToTable(String[] attributes, Node n) {
		Table subtreeTable = new Table(" ", attributes);
		for (Node child : n.getChildren()) {
			Row record = subtreeToRow(attributes, child);
			subtreeTable.addRow(record);
		}
		return subtreeTable;
	}
=======
>>>>>>> cf3af1e02443c454064e787fc211f4e9acdf9cf8
}
    	
    


