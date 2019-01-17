


package relationalCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import tree.Node;
import tree.Tree;

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
        String row = "";
        for (Row x : this.rows) {
            row = row + x.toString() + "\n";
        }
        return row;
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

    public Tree tabletoTree() {
        Node root = new Node(null, this.name);
        Tree tree = new Tree(root);
        for (Row row : this.rows) {
            Tree subtree = row.RowtoTree("row_name: " + Integer.toString(row.hashCode()));
            tree.mergeSubtreeToRoot(subtree);
        }
        return tree;
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
