package tree;

import relationalCategory.*;

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

    public void mergeSubtreeToRoot(Tree tree) {
        tree.getRoot().setParent(this.root);
        this.root.addChild(tree.getRoot());
    }

    public Row subtreeToRow(String[] attributes, Node n) {
        int l = attributes.length;
        String[] row = new String[l];
        for (int i = 0; i < l; i++) {
            if (!n.getChildren().isEmpty()) {
                if (n.getChildren().get(i).getData() instanceof String[]) {
                    String[] value = (String[]) n.getChildren().get(i).getData();
                    row[i] = value[1];
                } else if (n.getChildren().get(i).getData() instanceof String) {
                    String value = (String) n.getChildren().get(i).getChildren().get(0).getData();
                    row[i] = value;
                }
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
}
