package graphCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.jgrapht.graph.DefaultDirectedGraph;
import relationalCategory.Row;
import relationalCategory.Table;

public class GraphToEdgeVerticeTables {

    private final DefaultDirectedGraph graph;

    public GraphToEdgeVerticeTables(DefaultDirectedGraph graph_beginning) {
        this.graph = graph_beginning;
    }

    public Table[] getVerticeTables() {
        ArrayList<Table> tableList = new ArrayList<>();
        HashMap<Set<String>, Table> tableMap = new HashMap<>();
        for (Object vertex1 : this.graph.vertexSet()) {
            if (vertex1 instanceof DataContainerVertex) {
                DataContainerVertex vertex = (DataContainerVertex) vertex1;
                String[] attributes = vertex.getAttributes().stream().toArray(String[]::new);
                String[] data = new String[attributes.length];
                for (int i = 0; i < attributes.length; i++) {
                    data[i] = vertex.getDataAndAttributes().get(attributes[i]);
                }
                Row row = new Row(attributes, data);
                if (tableMap.containsKey(vertex.getAttributes())) {
                    Table newTable = tableMap.get(vertex.getAttributes());
                    newTable.addRow(row);
                } else {
                    Table newTable = new Table(vertex.getNodeType(), attributes);
                    newTable.addRow(row);
                    tableList.add(newTable);
                    Set<String> attributesSet = new HashSet<>(Arrays.asList(attributes));
                    tableMap.put(attributesSet, newTable);
                }
            }
        }
        Table[] tableArray = tableList.toArray(new Table[0]);
        return tableArray;
    }

    public Table[] getEdgeTables() {
        ArrayList<Table> edgeList = new ArrayList<>();
        HashMap<Set<String>, Table> tableMap = new HashMap<>();

        for (Object edge1 : this.graph.edgeSet()) {
            DataContainerVertex source = (DataContainerVertex) this.graph.getEdgeSource(edge1);
            DataContainerVertex target = (DataContainerVertex) this.graph.getEdgeTarget(edge1);
            if (edge1 instanceof DataContainerEdge) {
                DataContainerEdge edge = (DataContainerEdge) edge1;
                String[] firstAttributes = edge.getAttributes().stream().toArray(String[]::new);
                String[] sourceTarget = {"source", "target"};
                String[] attributes = concat(firstAttributes, sourceTarget);
                String[] data = new String[attributes.length];
                for (int i = 0; i < firstAttributes.length; i++) {
                    data[i] = edge.getDataAndAttributes().get(attributes[i]);
                }
                data[firstAttributes.length] = source.getDataAndAttributes().toString();
                data[firstAttributes.length + 1] = target.getDataAndAttributes().toString();
                Row row = new Row(attributes, data);
                if (tableMap.containsKey(edge.getAttributes())) {
                    Table newTable = tableMap.get(edge.getAttributes());
                    newTable.addRow(row);
                } else {
                    Table newTable = new Table(edge.getEdgeType(), attributes);
                    newTable.addRow(row);
                    edgeList.add(newTable);
                    Set<String> attributesSet = new HashSet<>(Arrays.asList(attributes));
                    tableMap.put(attributesSet, newTable);
                }
            }
        }
        Table[] tableArray = edgeList.toArray(new Table[0]);
        return tableArray;
    }

    private String[] concat(String[] first, String[] second) {
        int sum = first.length + second.length;
        String[] newString = new String[sum];
        for (int i = 0; i < sum; i++) {
            if (i < first.length) {
                newString[i] = first[i];
            } else {
                newString[i] = second[i - first.length];
            }
        }
        return newString;
    }
}
