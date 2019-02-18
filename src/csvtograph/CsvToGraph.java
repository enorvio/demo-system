package csvtograph;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

import graph.DataContainerEdge;
import graph.DataContainerVertex;

public class CsvToGraph {

    public CsvToGraphPreparation preparation;
    public String EdgesFilename;
    public String NodesFilename;
    public DefaultDirectedGraph graph;
    public ArrayList<DataContainerEdge> rawEdges;
    public ArrayList<DataContainerVertex> defaultVertices;
    public ArrayList<DataContainerVertex> shownVertices;
    public ArrayList<String> labels;

    public CsvToGraph(String EdgesFilename, String NodesFilename) throws FileNotFoundException {
        this.EdgesFilename = EdgesFilename;
        this.NodesFilename = NodesFilename;
        this.preparation = new CsvToGraphPreparation(this.EdgesFilename, this.NodesFilename);
        //this.preparation.print();
        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.rawEdges = preparation.getEdges();
        this.defaultVertices = preparation.getVertices();
        // the last row means that the default is that every vertex shall be displayed. this can be changed by Select
        //Vertices
        this.labels = new ArrayList<>();
    }

    public void SelectVertexAttributeShown(DataContainerVertex vertex, String attribute, boolean showBoth) {
        if (this.defaultVertices.contains(vertex) && vertex.getDataAndAttributes().containsKey(attribute)) {
            String label = vertex.getDataAndAttributes().get(attribute);
            if (showBoth) {
                label = attribute + ": " + label;
            }
            this.labels.add(attribute + ": " + label);
            this.graph.addVertex(label);
        }
    }

    public ArrayList<DataContainerEdge> getEdges() {
        return this.rawEdges;
    }

    public void selectAllDefaultLabels() {
        for (DataContainerVertex v : this.defaultVertices) {
            String label = v.getDataAndAttributes().get(v.getNodeType());
            this.labels.add(v.getNodeType() + ": " + label);
            this.graph.addVertex(v.getNodeType() + ": " + label);
        }
    }

    public void addConnectionManually(DataContainerVertex v1, DataContainerEdge e, DataContainerVertex v2) {
        this.graph.addVertex(v1);
        this.graph.addVertex(v2);
        if (this.defaultVertices.contains(v1) && this.defaultVertices.contains(v2) && this.rawEdges.contains(e)) {
            this.graph.addEdge(v1, v2, e);
        }
    }

    public DefaultDirectedGraph<Object, DefaultEdge> getGraph() {
        return this.graph;
    }

    public ListenableGraph<Object, DefaultEdge> getListenableGraph() {
        ListenableGraph<Object, DefaultEdge> listenableGraph = new DefaultListenableGraph<>(this.graph);
        return listenableGraph;
    }

    public ArrayList<DataContainerVertex> getVertices() {
        return this.defaultVertices;
    }
}
