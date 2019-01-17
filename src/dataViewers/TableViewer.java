package dataViewers;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import relationalCategory.Row;
import relationalCategory.Table;

public class TableViewer {
    
    private JFrame frame;
    
    public TableViewer(Table table) {
        this.frame = new JFrame("Tree View: " + table.toString());
        frame.setSize(800, 800);
        frame.getContentPane().setLayout(new BorderLayout());
        Object[][] dataRows = new Object[table.getRows().size()][table.getAttributes().length];
        int i = 0;
        for (Row row : table.getRows()) {
            dataRows[i] = row.getRow();
            i++;
        }
        JTable graphicTabel = new JTable(dataRows, table.getAttributes());
        JScrollPane scrollPane = new JScrollPane(graphicTabel);
        frame.getContentPane().add("Center", scrollPane);
        frame.setVisible(true);
    }

}
