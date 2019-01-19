package relationalCategory;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
    public DefaultTreeModel constructSubtableCategory() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.tables.get(0));
        DefaultTreeModel tree = new DefaultTreeModel(root);
        for (int i = 1; i < this.tables.size(); i++) {
            findLocationInTree(this.tables.get(i), root);
        }
        return tree;
    }

    private void findLocationInTree(Table table, DefaultMutableTreeNode current_node) {
        ArrayList<DefaultMutableTreeNode> children = Collections.list(current_node.children());
        if (children.isEmpty()) {
            DefaultMutableTreeNode new_node = new DefaultMutableTreeNode(table);
            current_node.add(new_node);
        } else {
            int i = 0;
            for (DefaultMutableTreeNode child : children) {
                if (null == table.compare((Table) child.getUserObject())) {
                    i++;
                    if (i == children.size()) {
                        DefaultMutableTreeNode new_node = new DefaultMutableTreeNode(table);
                        current_node.add(new_node);
                        break;
                    }
                } else {
                    switch (table.compare((Table) child.getUserObject())) {
                        case 0:
                            break;
                        case 1:
                            current_node = child;
                            findLocationInTree(table, current_node);
                            break;
                        case -1:
                            DefaultMutableTreeNode new_node = new DefaultMutableTreeNode(table);
                            child.setParent(new_node);
                            current_node.remove(child);
                            current_node.add(new_node);
                            new_node.add(child);
                            break;
                    }
                }
            }
        }
    }

}
