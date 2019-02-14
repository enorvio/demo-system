package dataViewers.graphViewer;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import com.mxgraph.view.mxGraphView;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;

public class JGraphXAdapterHandler extends JApplet {

    private static final Dimension DEFAULT_SIZE = new Dimension(800, 800);

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

    public void initialize(ListenableGraph g) {
        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(g);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        //mxGraphView view = component.getGraph().getView();
        //int compLen = component.getWidth();
        //int viewLen = (int) view.getGraphBounds().getWidth();
        //view.setScale((double) compLen / viewLen * view.getScale());
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        component.zoomAndCenter();
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 100;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());


    }
}
