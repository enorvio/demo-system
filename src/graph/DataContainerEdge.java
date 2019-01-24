package graph;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class DataContainerEdge implements Serializable {

    private String edgeType;
    private HashMap<String, String> dataAttributes;

    public DataContainerEdge(String edgeType1, HashMap<String, String> dataAttributes1) {
        this.edgeType = edgeType1;
        this.dataAttributes = dataAttributes1;
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

    public Collection<String> getValues() {
        return this.dataAttributes.values();
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
