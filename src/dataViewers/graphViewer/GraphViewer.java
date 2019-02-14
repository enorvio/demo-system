package dataViewers.graphViewer;

import java.awt.Dimension;
import javax.swing.JPanel;
import org.jgrapht.ListenableGraph;

public class GraphViewer {
    
    private JPanel pane;
    
    public GraphViewer(ListenableGraph g) {
        JGraphXAdapterHandler applet = new JGraphXAdapterHandler();
        applet.initialize(g);
        Dimension d = applet.getSize();
        System.out.println(d);
        this.pane = new JPanel();
        this.pane.add(applet);
    }
    
    public JPanel getGraphPanel() {
        return this.pane;
    }

}
