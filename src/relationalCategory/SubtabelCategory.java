/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relationalCategory;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Valter Uotila
 */
public class SubtabelCategory {
    
    private final ArrayList<Table> tables;
    private final String name;
    
    public SubtabelCategory(String name_beginning) {
        this.tables = new ArrayList<>();
        this.name = name_beginning;
    }
    
    public void addTable(Table table) {
        this.tables.add(table);
    }
    
    public String getName() {
        return this.name;
    }
    
    public TreeSet constructSubtableCategory() {
        TreeSet<Table> tree = new TreeSet<>(this.tables);
        return tree;
    }
    
}
