package relationalCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Valter
 */
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
}
