package relationalCategory;

/**
 *
 * @author Valter
 */
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
    
    @Override
    public String toString() {
        String row_string = "";
        
        for(String x : this.attributes){
            row_string = row_string + x + " ";
        }
        row_string = row_string + "\n";
        for(String x : this.row) {
            row_string = row_string + x + " ";
        }
        return row_string;
    }
    
}
