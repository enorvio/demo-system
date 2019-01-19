package dataViewers;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import relationalCategory.Row;
import relationalCategory.Table;

public class TableViewer {
    
    private JScrollPane scrollPane;
    
    public TableViewer(Table table) {
        Object[][] dataRows = new Object[table.getRows().size()][table.getAttributes().length];
        int i = 0;
        for (Row row : table.getRows()) {
            dataRows[i] = row.getRow();
            i++;
        }
        JTable graphicTabel = new JTable(dataRows, table.getAttributes());
        this.scrollPane = new JScrollPane(graphicTabel);
    }
    
    public JScrollPane getGraphicTable() {
        return this.scrollPane;
    }

}
