package tree;

import graph.DataContainerVertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

public class TreeToGraph {

    public DefaultMutableTreeNode root;
    private DefaultDirectedGraph<Object, DefaultEdge> graph;

    public TreeToGraph(DefaultMutableTreeNode rootBeginning) {
        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        this.root = rootBeginning;
    }

    public DefaultDirectedGraph<Object, DefaultEdge> getDirectedGraph() {
        HashMap<String, String> rootmap = new HashMap();
        rootmap.put(this.root.toString(), this.root.getUserObject().toString());
        DataContainerVertex v = new DataContainerVertex("root", rootmap);
        this.graph.addVertex(v);
        walkTree(this.root);
        return this.graph;
    }

    public ListenableGraph<Object, DefaultEdge> getListenableGraph() {
        this.getDirectedGraph();
        ListenableGraph<Object, DefaultEdge> listenableGraph = new DefaultListenableGraph<>(this.graph);
        return listenableGraph;
    }
    
    private void walkTree(DefaultMutableTreeNode root) {
        ArrayList<DefaultMutableTreeNode> children = Collections.list(root.children());
        for(DefaultMutableTreeNode child : children) {
            HashMap<String, String> map = new HashMap();
            map.put(child.toString(), child.getUserObject().toString());
            DataContainerVertex v = new DataContainerVertex(child.toString(), map);
            this.graph.addVertex(v);
            walkTree(child);
        }
    }
}
