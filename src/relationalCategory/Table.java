package relationalCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

public class Table implements Comparable<Table> {

    private final String[] attributes;
    private final HashSet<Row> rows;
    private final String name;

    public Table(String name_beginning, String[] attributes_beginning) {
        this.rows = new HashSet();
        this.name = name_beginning;
        this.attributes = attributes_beginning;
    }

    public HashSet<Row> getRows() {
        return this.rows;
    }

    public String[] getAttributes() {
        return this.attributes;
    }

    public String getName() {
        return this.name;
    }

    public void addRow(Row record) {
        if (record.getAttributes() == this.attributes) {
            this.rows.add(record);
        }
    }

    private boolean containsAllRows(HashSet<Row> rows) {
        int i = 0;
        for (Row row1 : this.rows) {
            for (Row row2 : rows) {
                if (contains(row2.getRow(), row1.getRow()) == true) {
                    i++;
                }
            }
        }
        if (i >= this.rows.size()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
//        String row = "";
//        for (Row x : this.rows) {
//            row = row + x.toString() + "\n";
//        }
//        return row;
    }

    //This compering method induces partial order to the collection of Tables (subset partial order). If tables are not comparable, method returns null.
    public Integer compare(Table table) {
        if (Arrays.equals(this.attributes, table.getAttributes()) && this.rows.equals(table.getRows())) {
            return 0;
        } else if (contains(this.attributes, table.getAttributes()) && table.containsAllRows(this.rows)) {
            return -1;
        } else if (contains(table.getAttributes(), this.attributes) && this.containsAllRows(table.getRows())) {
            return 1;
        } else {
            return null;
        }
    }

    private boolean contains(String[] list, String[] sublist) {
        List<String> list1 = new ArrayList<>(Arrays.asList(list));
        List<String> list2 = new ArrayList<>(Arrays.asList(sublist));
        return Collections.indexOfSubList(list1, list2) != -1;
    }

    @Override
    public int compareTo(Table o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultMutableTreeNode tabletoTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.name);
        for (Row row : this.rows) {
            DefaultMutableTreeNode subroot = row.RowtoTree("row_name: " + Integer.toString(row.hashCode()));
            root.add(subroot);
        }

        //printTree(root, 0);
        return root;
    }

    private void printTree(DefaultMutableTreeNode root, int i) {
        System.out.println(String.valueOf(i) + " " + root.toString());
        ArrayList<DefaultMutableTreeNode> children = Collections.list(root.children());
        i++;
        for (DefaultMutableTreeNode child : children) {
            printTree(child, i);
        }
    }

    public void printGraphicTable() {
        int max = 0;
        for (String attribute : this.attributes) {
            if (attribute.length() > max) {
                max = attribute.length();
            }
        }
        for (Row row : this.rows) {
            for (String entry : row.getRow()) {
                if (entry != null) {
                    if (entry.length() > max) {
                        max = entry.length();
                    }
                }
            }
        }

        String leftAlignFormat = "| ";
        String upAlignFormat = "+ ";
        for (String attribute : this.attributes) {
            leftAlignFormat += " %-" + Integer.toString(max) + "s |";
            upAlignFormat += " %-" + Integer.toString(max) + "s +";
        }
        leftAlignFormat += "%n";
        upAlignFormat += "%n";

        String line = "";
        for (int i = 0; i < max; i++) {
            line += "-";
        }

        String[] lines = new String[this.attributes.length];
        Arrays.fill(lines, line);

        System.out.format(upAlignFormat, (Object[]) lines);
        System.out.format(leftAlignFormat, (Object[]) this.attributes);
        System.out.format(upAlignFormat, (Object[]) lines);
        for (Row row : this.rows) {
            System.out.format(leftAlignFormat, (Object[]) row.getRow());
        }
        System.out.format(upAlignFormat, (Object[]) lines);
    }
}
