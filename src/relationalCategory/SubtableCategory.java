package relationalCategory;

import java.util.ArrayList;
import tree.Node;
import tree.Tree;

/**
 *
 * @author Valter
 */
public class SubtableCategory {

    private final ArrayList<Table> tables;
    private final String name;

    public SubtableCategory(String name_beginning) {
        this.tables = new ArrayList<>();
        this.name = name_beginning;
    }

    public void addTable(Table table) {
        this.tables.add(table);
    }

    public String getName() {
        return this.name;
    }

    //Assuming that root (the largest table) is in the first place of the list.
    public Tree constructSubtableCategory() {
        Tree tree = new Tree(this.tables.get(0));
        Node root = tree.getRoot();
        for (int i = 1; i < this.tables.size(); i++) {
            findLocationInTree(this.tables.get(i), root);
        }
        return tree;
    }

    private void findLocationInTree(Table table, Node current_node) {
        ArrayList<Node> children = current_node.getChildren();
        if (children.isEmpty()) {
            Node new_node = new Node(current_node, table);
            current_node.addChild(new_node);
        } else {
            int i = 0;
            for (Node child : children) {
                if (null == table.compare((Table) child.getData())) {
                    i++;
                    if (i == children.size()) {
                        Node new_node = new Node(current_node, table);
                        current_node.addChild(new_node);
                        break;
                    }
                } else {
                    switch (table.compare((Table) child.getData())) {
                        case 0:
                            break;
                        case 1:
                            current_node = child;
                            findLocationInTree(table, current_node);
                            break;
                        case -1:
                            Node new_node = new Node(current_node, table);
                            child.setParent(new_node);
                            current_node.removeChild(child);
                            current_node.addChild(new_node);
                            new_node.addChild(child);
                            break;
                    }
                }
            }
        }
    }

}
