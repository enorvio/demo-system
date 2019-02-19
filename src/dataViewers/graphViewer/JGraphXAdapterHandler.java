package dataViewers.graphViewer;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.*;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraphView;
import com.mxgraph.view.mxStylesheet;
import graph.DataContainerVertex;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class JGraphXAdapterHandler extends JApplet {

    private static final Dimension DEFAULT_SIZE = new Dimension(1850, 800);
    private static final String[] COLORS = {"#e0d48f", "#a2e5cd", "#99a8db", "#e0a6bb", "#cce0a6", "#b1ed40", "#a1bfed"};

    private JGraphXAdapter<Object, DefaultEdge> jgxAdapter;

    public void initialize(ListenableGraph g) {
        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(g);
        jgxAdapter.setAutoSizeCells(true);
        setPreferredSize(DEFAULT_SIZE);

        Map<String, Object> edgeStyle = new HashMap<String, Object>();
        edgeStyle.put(mxConstants.STYLE_ROUNDED, true);
        edgeStyle.put(mxConstants.STYLE_ORTHOGONAL, false);
        edgeStyle.put(mxConstants.STYLE_EDGE, "elbowEdgeStyle");
        edgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        edgeStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
        edgeStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        edgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
        edgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#446299");

        mxStylesheet styleSheet = jgxAdapter.getStylesheet();

        Map<String, Object> vertexStyle = styleSheet.getDefaultVertexStyle();
        vertexStyle.put(mxConstants.STYLE_ROUNDED, "1");
        vertexStyle.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
        vertexStyle.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        vertexStyle.put(mxConstants.STYLE_RESIZABLE, "0");
        vertexStyle.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        vertexStyle.put(mxConstants.STYLE_FILLCOLOR, "#7fefa6");

        styleSheet.setDefaultEdgeStyle(edgeStyle);
        jgxAdapter.setStylesheet(styleSheet);

        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        component.zoomAndCenter();
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        Object root = jgxAdapter.getDefaultParent();
        //mxICell root = vertexToCell.values().iterator().next();
        System.out.println(root);
        //walkGraph(root, 0);

        // positioning via jgraphx layouts
        mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);

        this.addMouseWheelListener(e -> {
            mxGraphView view = component.getGraph().getView();
            int notches = e.getWheelRotation();
            double scale = view.getScale();
            double newScale = view.getScale() - ((double) notches / 61.8033988272);
            view.setScale(newScale);
        });

        component.getGraphControl().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mxICell cell = (mxICell) component.getCellAt(e.getX(), e.getY());
                System.out.println("Mouse clicked the graph component");
                if (cell != null) {
                    System.out.println("cell = " + jgxAdapter.getLabel(cell));
                    if (cell.getValue() instanceof DataContainerVertex) {
                        DataContainerVertex value = (DataContainerVertex) cell.getValue();
                        cell.setValue(value.getNodeType() + " " + value.getDataAndAttributes());
                        jgxAdapter.updateCellSize(cell);
                        component.refresh();
                    }

                }
            }

        });

        //Another possibility to add eventlistener to the cells:
        /*jgxAdapter.getSelectionModel().addListener(mxEvent.CHANGE, new mxIEventListener() {

            @Override
            public void invoke(Object sender, mxEventObject evt) {
                mxGraphSelectionModel sm = (mxGraphSelectionModel) sender;
                mxCell cell = (mxCell) sm.getCell();
                if (cell != null && cell.isVertex()) {
                    System.out.println("fdfdafdsafds");
                }
            }
        });*/
        layout.execute(jgxAdapter.getDefaultParent());

    }

    private void walkGraph(mxICell rootCell,  int j) {
        int i = 0;
        while (true) {
            try {
               System.out.println("-------------");
               mxICell childCell = rootCell.getChildAt(i);
               System.out.println(childCell);
               childCell.setStyle(COLORS[j]);
               walkGraph(childCell, j+1);
               i++;
            } catch (Exception error) {
                break;
            }
        }
    }
}
