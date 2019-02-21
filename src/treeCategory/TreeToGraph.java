package treeCategory;

import graphCategory.DataContainerEdge;
import graphCategory.DataContainerVertex;
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
        walkTree(this.root, v);
        System.out.println(this.graph.vertexSet().size());
        return this.graph;
    }

    public ListenableGraph<Object, DefaultEdge> getListenableGraph() {
        this.getDirectedGraph();
        ListenableGraph<Object, DefaultEdge> listenableGraph = new DefaultListenableGraph<>(this.graph);
        return listenableGraph;
    }

    private void walkTree(DefaultMutableTreeNode root, DataContainerVertex parentVertex) {
        ArrayList<DefaultMutableTreeNode> children = Collections.list(root.children());
        HashMap<String, String> map = new HashMap<>();
        ArrayList<DefaultMutableTreeNode> children2 = new ArrayList<>();
        for (DefaultMutableTreeNode child : children) {
            ArrayList<DefaultMutableTreeNode> leafChildren = Collections.list(child.children());
            if (leafChildren.get(0).isLeaf()) {
                map.put(child.getUserObject().toString(), leafChildren.get(0).getUserObject().toString());
            } else {
                children2.add(child);
            }
        }
        DataContainerEdge e = new DataContainerEdge("edge");
        if (map.isEmpty() == false) {
            DataContainerVertex v = new DataContainerVertex(root.toString(), map);
            this.graph.addVertex(v);
            this.graph.addEdge(v, parentVertex);
            for (DefaultMutableTreeNode child : children2) {
                walkTree(child, v);
            }
        } else {
            for (DefaultMutableTreeNode child : children2) {
                walkTree(child, parentVertex);
            }

        }
    }
}
