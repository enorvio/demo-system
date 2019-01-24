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
import java.util.HashSet;
import java.util.Scanner;
import graph.*;
import relationalCategory.*;

public class CsvToGraphPreparation {
	private String csvEdge;
	private String csvNode;
	private HashSet<graph.Edge> edges;
	private Table nodetable;

	public CsvToGraphPreparation(String csvEdgeFilename, String csvNodeFilename) throws FileNotFoundException{
        this.csvEdge = csvEdgeFilename;
        this.csvNode = csvNodeFilename;
        this.edges = new HashSet<graph.Edge>();
        Scanner reader1 = new Scanner(new File(this.csvEdge));
	    String[] attributes1 = reader1.nextLine().split(",");
	       while (reader1.hasNextLine()) {
	           String[] row1 = reader1.nextLine().split(",");
	           graph.Edge edge = new graph.Edge(attributes1, row1);
	           this.edges.add(edge);
	                }
	    Scanner reader2 = new Scanner(new File(this.csvNode));
		String[] attributes2 = reader2.nextLine().split(",");
		this.nodetable = new Table(csvNode, attributes2);
		    while (reader2.hasNextLine()) {
		        String[] row = reader2.nextLine().split(",");
		        Row newRow = new Row(attributes2, row);
		        this.nodetable.addRow(newRow);
		            }
		       
	        }
		public Table getNodeTable () {
			return this.nodetable;
		}
		
		public HashSet<graph.Edge> getEdges () {
			return this.edges;
		}
	
	    }
	
	

