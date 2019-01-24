package csvtograph;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import graph.*;
import relationalCategory.*;

public class CsvToGraphPreparation {
	private ArrayList<graph.DataContainerEdge> edges;
	private ArrayList<graph.DataContainerVertex> vertices;
	private String csvEdge;
	private String csvNode;
	
	

	public CsvToGraphPreparation(String csvEdgeFilename, String csvNodeFilename) throws FileNotFoundException{
        this.csvEdge = csvEdgeFilename;
        this.csvNode = csvNodeFilename;
        this.edges = new ArrayList<graph.DataContainerEdge>();
        this.vertices = new ArrayList<graph.DataContainerVertex>();
        Scanner reader1 = new Scanner(new File(this.csvEdge));
	    String attributes1 = reader1.nextLine();
	    //here I am assuming that the first line always is just id,EdgeType,property_key,property_value,property_key,property_value etc. and discard it
	    while (reader1.hasNextLine()) {
	    	   HashMap<String, String> currentAttributes = new HashMap<String, String>();
	           String[] currentRow = reader1.nextLine().split(",");
	           String currentType = currentRow[1];
	           // here I am making an assumption that there always is a value for each key so the length is even.
	           for (int i=1; i < currentRow.length-1; i+=2) {
	        	   currentAttributes.put(currentRow[i], currentRow[i+1]);
	           }
	           graph.DataContainerEdge currentEdge = new graph.DataContainerEdge(currentType, currentAttributes);
	           this.edges.add(currentEdge);
	           	}
	    		reader1.close();
	           
	   Scanner reader2 = new Scanner(new File(this.csvNode));
	   String attributes2 = reader2.nextLine();
			   while (reader2.hasNextLine()) {
			   HashMap<String, String> currentAttributes = new HashMap<String, String>();
		       String[] currentRow = reader2.nextLine().split(",");
		       String currentType = currentRow[1];
		       for (int i=1; i < currentRow.length-1; i+=2) {
	        	   currentAttributes.put(currentRow[i], currentRow[i+1]);
	           }
	           graph.DataContainerVertex currentVertex = new graph.DataContainerVertex(currentType, currentAttributes);
	           this.vertices.add(currentVertex);
	           	}
			   reader2.close();
			   
	   
	}
	
	public void print() {
		System.out.println("Edges:");
		for (graph.DataContainerEdge e : this.edges) {
			e.print();
		}
		System.out.println("Vertices:");
		for (graph.DataContainerVertex v : this.vertices) {
			v.print();
		}
	}
	
	public ArrayList<graph.DataContainerEdge> getEdges() {
		return this.edges;
	}
	
	public ArrayList<graph.DataContainerVertex> getVertices() {
		return this.vertices;
	}
	
}
		       

	
	    
	
	

