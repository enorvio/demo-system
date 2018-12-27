/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relationalCategory;

import relationalCategory.Row;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Valter Uotila
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
        if(record.getAttributes() == this.attributes) {
            this.rows.add(record);
        }
    }
    
    @Override
    public String toString() {
        String row = "";
        for(Row x: this.rows) {
            row = row + x.toString() + "\n";
        }
        return row;
    }

    @Override
    public int compareTo(Table table) {
        if(this.attributes == table.getAttributes() && this.rows.equals(table.getRows())) {
            return 0;
        } else if (contains(this.attributes, table.getAttributes()) && this.rows.containsAll(table.getRows())) {
            return 1;
        } else {
            return -1;
        }
    }
    
    private boolean contains(String[] list, String[] sublist) {
        List<String> list1 = new ArrayList<>(Arrays.asList(list));
        List<String> list2 = new ArrayList<>(Arrays.asList(sublist));
        return Collections.indexOfSubList(list1, list2) != -1;
  }
}
