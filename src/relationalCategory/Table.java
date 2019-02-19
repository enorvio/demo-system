package relationalCategory;

import graphCategory.DataContainerVertex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;

public class Table implements Comparable<Table> {

    private final String[] attributes;
    private final HashSet<Row> rows;
    private final String name;
    private final DefaultDirectedGraph<Object, DefaultEdge> graph;

    public Table(String name_beginning, String[] attributes_beginning) {
        this.rows = new HashSet();
        this.name = name_beginning;
        this.attributes = attributes_beginning;
        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    public HashSet<Row> getRows() {
        return this.rows;
    }

    public String[] getAttributes() {
        return this.attributes;
    }

    public String getName() {
        return this.name;
    }

    public boolean addRow(Row record) {
        if (Arrays.equals(record.getAttributes(), this.attributes)) {
            this.rows.add(record);
            return true;
        } else {
            return false;
        }
    }

    public void addRows(HashSet<Row> rows) {
        if (Arrays.equals(rows.iterator().next().getAttributes(), this.attributes)) {
            this.rows.addAll(rows);
        }
    }

    private boolean containsAllRows(HashSet<Row> rows) {
        int i = 0;
        for (Row row1 : this.rows) {
            for (Row row2 : rows) {
                if (contains(row2.getRow(), row1.getRow()) == true) {
                    i++;
                }
            }
        }
        if (i >= this.rows.size()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }

    //This compering method induces partial order to the collection of Tables (subset partial order). If tables are not comparable, method returns null.
    public Integer compare(Table table) {
        if (Arrays.equals(this.attributes, table.getAttributes()) && this.rows.equals(table.getRows())) {
            return 0;
        } else if (contains(this.attributes, table.getAttributes()) && table.containsAllRows(this.rows)) {
            return -1;
        } else if (contains(table.getAttributes(), this.attributes) && this.containsAllRows(table.getRows())) {
            return 1;
        } else {
            return null;
        }
    }

    private boolean contains(String[] list, String[] sublist) {
        List<String> list1 = new ArrayList<>(Arrays.asList(list));
        List<String> list2 = new ArrayList<>(Arrays.asList(sublist));
        return Collections.indexOfSubList(list1, list2) != -1;
    }

    @Override
    public int compareTo(Table o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DefaultMutableTreeNode tabletoTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(this.name);
        for (Row row : this.rows) {
            DefaultMutableTreeNode subroot = row.RowtoTree("row_name: " + Integer.toString(row.hashCode()));
            root.add(subroot);
        }
        return root;
    }

    public DefaultDirectedGraph<Object, DefaultEdge> getDirectedGraph() {
        for (Row row : this.rows) {
            HashMap<String, String> map = new HashMap();
            for (int i = 0; i < row.getRow().length; i++) {
                map.put(row.getAttributes()[i], row.getRow()[i]);
            }
            DataContainerVertex v = new DataContainerVertex("row", map);
            this.graph.addVertex(v);
        }
        return this.graph;
    }

    public ListenableGraph<Object, DefaultEdge> getListenableGraph() {
        this.getDirectedGraph();
        ListenableGraph<Object, DefaultEdge> listenableGraph = new DefaultListenableGraph<>(this.graph);
        return listenableGraph;
    }
}
