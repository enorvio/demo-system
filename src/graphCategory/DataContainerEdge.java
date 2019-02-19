package graphCategory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;

public class DataContainerEdge extends DefaultEdge implements Serializable {

    private String edgeType;
    private HashMap<String, String> dataAttributes;

    public DataContainerEdge(String edgeType1, HashMap<String, String> dataAttributes1) {
        this.edgeType = edgeType1;
        this.dataAttributes = dataAttributes1;
    }
    
    public DataContainerEdge(String edgeType1) {
        this.edgeType = edgeType1;
        this.dataAttributes = new HashMap();
    }

    public String getEdgeType() {
        return this.edgeType;
    }

    public HashMap<String, String> getDataAndAttributes() {
        return this.dataAttributes;
    }

    public Set<String> getAttributes() {
        return this.dataAttributes.keySet();
    }

    public Collection<String> getData() {
        return this.dataAttributes.values();
    }
    
    public void print() {
    	System.out.println("type" + this.edgeType);
    	for (String k : this.dataAttributes.keySet()) {
    		System.out.println(k +" " + this.dataAttributes.get(k));
    	}
    }
    
    @Override
    public String toString() {
        return this.edgeType;
    }

    @Override
    public int hashCode() {
        return this.edgeType.hashCode() + this.dataAttributes.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof DataContainerEdge) {
            DataContainerEdge object = (DataContainerEdge) o;
            if(this.edgeType.equals(object.getEdgeType()) && this.dataAttributes.equals(object.getDataAndAttributes())) {
                return true;
            }
        }
        return false;
    }
}
