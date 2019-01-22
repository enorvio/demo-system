package dataViewers.graphViewer;

import javax.swing.JPanel;
import org.jgrapht.ListenableGraph;

public class GraphViewer {
    
    private JPanel pane;
    
    public GraphViewer(ListenableGraph g) {
        JGraphXAdapterHandler applet = new JGraphXAdapterHandler();
        applet.initialize(g);
        this.pane = new JPanel();
        this.pane.add(applet);
    }
    
    public JPanel getGraphPanel() {
        return this.pane;
    }

}
