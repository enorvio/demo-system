
package tree;

import java.util.ArrayList;
import relationalCategory.Table;

public class Node {

    private Object data;
    private Node parent;
    private ArrayList<Node> children;

    public Node(Node parent, Object data) {
        this.parent = parent;
        this.data = data;
        this.children = new ArrayList<Node>();
    }

    public Node getParent() {
        return this.parent;
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Object getData() {
        return this.data;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void removeChild(Node child) {
        this.children.remove(child);
    }

    public void printNode() {
        System.out.println(this.toString());
        for (Node c : this.children) {
            c.printNode();
        }
    }

    public void printGraphicNode() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + this.toString());
        for (int i = 0; i < this.children.size() - 1; i++) {
            this.children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
        }
        if (this.children.size() > 0) {
            this.children.get(this.children.size() - 1)
                    .print(prefix + (isTail ? "    " : "│   "), true);
        }
    }

    @Override
    public String toString() {
        String s = "";
        if (this.data instanceof String[]) {
            String[] data = (String[]) this.data;
            s = data[0] + " " + data[1] + " " + this.children.size();
            if (this.parent != null) {
                return this.parent.toString() + " " + s;
            }
        }
        if (this.data instanceof Table) {
            Table table = (Table) this.data;
            s = table.getName();
        }
        return s;
    }

}
