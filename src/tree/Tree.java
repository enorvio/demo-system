package tree;

public class Tree {

    private Node root;

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

    public void printTree() {
        this.root.printNode();
    }
   
    public void printGraphicTree() {
        this.root.printGraphicNode();
    }
}
