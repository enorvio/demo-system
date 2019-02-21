package graphCategory;

import javax.swing.tree.DefaultMutableTreeNode;
import org.jgrapht.graph.DefaultDirectedGraph;

public class GraphToTree {

    private final DefaultDirectedGraph graph;

    public GraphToTree(DefaultDirectedGraph graph_beginning) {
        this.graph = graph_beginning;
    }

    public DefaultMutableTreeNode getTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode vertices = new DefaultMutableTreeNode("Vertices");
        root.add(vertices);
        for (Object v : this.graph.vertexSet()) {
            DataContainerVertex vertex = (DataContainerVertex) v;
            DefaultMutableTreeNode vertexTypeNode = new DefaultMutableTreeNode(vertex.getNodeType());
            vertices.add(vertexTypeNode);
            for (String attribute : vertex.getAttributes()) {
                DefaultMutableTreeNode attributeNode = new DefaultMutableTreeNode(attribute);
                DefaultMutableTreeNode dataNode = new DefaultMutableTreeNode(vertex.getDataAndAttributes().get(attribute));
                attributeNode.add(dataNode);
                vertexTypeNode.add(attributeNode);
            }
        }
        DefaultMutableTreeNode edges = new DefaultMutableTreeNode("Edges");
        root.add(edges);
        for (Object e : this.graph.edgeSet()) {
            DataContainerEdge edge = (DataContainerEdge) e;
            DefaultMutableTreeNode edgeTypeNode = new DefaultMutableTreeNode(edge.getEdgeType());
            edges.add(edgeTypeNode);
            for (String attribute : edge.getAttributes()) {
                DefaultMutableTreeNode attributeNode = new DefaultMutableTreeNode(attribute);
                DefaultMutableTreeNode dataNode = new DefaultMutableTreeNode(edge.getDataAndAttributes().get(attribute));
                attributeNode.add(dataNode);
                edgeTypeNode.add(attributeNode);
            }
        }

        return root;
    }

}
