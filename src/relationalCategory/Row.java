package relationalCategory;

import javax.swing.tree.DefaultMutableTreeNode;
import tree.Node;
import tree.Tree;

public final class Row {

    private final String[] row;
    private final String[] attributes;

    public Row(String[] attributes_beginning, String[] row_beginning) {
        this.row = row_beginning;
        this.attributes = attributes_beginning;
    }

    public String[] getRow() {
        return this.row;
    }

    public String[] getAttributes() {
        return this.attributes;
    }

    public Tree RowtoTree(String root_name) {
        Node root = new Node(null, root_name);
        Tree tree = new Tree(root);
        for (int i = 0; i < this.row.length; i++) {
            Node attribute_node = new Node(root, this.attributes[i]);
            Node data_node = new Node(attribute_node, this.row[i]);
            attribute_node.addChild(data_node);
            root.addChild(attribute_node);
        }
        return tree;
    }
    
    public DefaultMutableTreeNode RowtoMutableTree(String root_name) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(root_name);
        for (int i = 0; i < this.row.length; i++) {
            DefaultMutableTreeNode attribute_node = new DefaultMutableTreeNode(this.attributes[i]);
            root.add(attribute_node);
            DefaultMutableTreeNode data_node = new DefaultMutableTreeNode(this.row[i]);
            attribute_node.add(data_node);
        }
        return root;
    }

    @Override
    public String toString() {
        String row_string = "";

        for (String x : this.attributes) {
            row_string = row_string + x + " ";
        }
        row_string = row_string + "\n";
        for (String x : this.row) {
            row_string = row_string + x + " ";
        }
        return row_string;
    }

}
