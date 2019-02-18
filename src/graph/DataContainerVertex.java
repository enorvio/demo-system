package graph;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class DataContainerVertex implements Serializable {

    private String nodeType;
    private HashMap<String, String> dataAttributes;

    public DataContainerVertex(String nodeType1, HashMap<String, String> dataAttributes1) {
        this.nodeType = nodeType1;
        this.dataAttributes = dataAttributes1;
    }

    public String getNodeType() {
        return this.nodeType;
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
    	System.out.println("type" + this.nodeType);
    	for (String k : this.dataAttributes.keySet()) {
    		System.out.println(k +" " + this.dataAttributes.get(k));
    	}
    }

    @Override
    public String toString() {
        return this.nodeType; // + " " + this.dataAttributes.keySet().toString() + " " + this.dataAttributes.values().toString();
    }

    @Override
    public int hashCode() {
        return this.nodeType.hashCode() + this.dataAttributes.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof DataContainerVertex) {
            DataContainerVertex object = (DataContainerVertex) o;
            if(this.nodeType.equals(object.getNodeType()) && this.dataAttributes.equals(object.getDataAndAttributes())) {
                return true;
            }
        }
        return false;
    }
}
