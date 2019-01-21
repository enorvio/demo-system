package GUI;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class DataFrame {

    private JFrame frame;

    public DataFrame(JComponent[] components) {
        this.frame = new JFrame("Data");
        frame.setSize(800, 800);
        frame.getContentPane().setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Tree", components[0]);
        for (int i = 1; i < components.length; i++) {
            tabbedPane.addTab("Table", components[i]);
        }
        frame.getContentPane().add("Center", tabbedPane);
        frame.setVisible(true);
    }

}
