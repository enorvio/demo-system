package relationalCategory;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Valter
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
