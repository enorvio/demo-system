package backend.repositories;

import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jgrapht.graph.DefaultDirectedGraph;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import backend.controllers.DefaultMutableTreeNodeSerializer;
import relationalCategory.Table;

public class JSONrepository {
	
	private Table[] tables;
	private GraphRepository graph;
	private String tree;
	
	public JSONrepository(Table[] tables, DefaultMutableTreeNode root, DefaultDirectedGraph dirgraph) {
		this.tables = tables;
		this.graph = new GraphRepository("graph", 100, dirgraph);
		
		String json = "";
		ObjectMapper jsonMapper = new ObjectMapper()
			    .registerModule(new SimpleModule()
			        .addSerializer(DefaultMutableTreeNode.class, new DefaultMutableTreeNodeSerializer()))
			    .enable(SerializationFeature.INDENT_OUTPUT);
		try {
			json = jsonMapper.writeValueAsString(root);
		} catch (IOException e) {
			e.printStackTrace();
			json = "Error";
		}
		
		this.tree = json;
	}

	public Table[] getTables() {
		return tables;
	}

	public void setTables(Table[] table) {
		this.tables = tables;
	}

	public GraphRepository getGraph() {
		return graph;
	}

	public void setGraph(GraphRepository graphrepo) {
		this.graph = graphrepo;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

}
