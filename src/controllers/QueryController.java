package backend.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang3.ArrayUtils;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.repositories.JSONrepository;
import graphCategory.GraphToEdgeVerticeTables;
import graphCategory.GraphToTree;
import queryButtonsLogic.GraphQueryButton;
import queryProcessing.SQLquery;
import queryProcessing.XPathQuery;
import relationalCategory.Table;
import treeCategory.TreeToGraph;
import treeCategory.TreeToTableFunctor;

@RestController
public class QueryController {

	@CrossOrigin
	@RequestMapping("/sql")
	public JSONrepository getSQL() {
		String[] attributes2 = { "id", "firstname", "surname" };
		SQLquery demo;
		try {
			demo = new SQLquery(
					new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\demoData\\persons\\Person.csv"));
			Table tableResult = demo.loadDemoQuery(attributes2, "933");
			DefaultMutableTreeNode treeResult = tableResult.tabletoTree();
			DefaultDirectedGraph graphResult = tableResult.getDirectedGraph();
			Table[] tables = {tableResult};
			return new JSONrepository(tables, treeResult, graphResult);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	@CrossOrigin
	@RequestMapping("/graph")
	public JSONrepository getGraph() {
		GraphQueryButton graph;
		try {
			graph = new GraphQueryButton();
			DefaultDirectedGraph graphResult = graph.getDataGraph();
			GraphToTree graphToTree = new GraphToTree(graphResult);
			DefaultMutableTreeNode treeResult = graphToTree.getTree();
			GraphToEdgeVerticeTables graphTables = new GraphToEdgeVerticeTables(graphResult);
			Table[] verticeTables = graphTables.getVerticeTables();
			Table[] edgeTables = graphTables.getEdgeTables();
			Table[] bothTables = (Table[]) ArrayUtils.addAll(verticeTables, edgeTables);
			return new JSONrepository(bothTables, treeResult, graphResult);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@CrossOrigin
	@RequestMapping("/document")
	public JSONrepository getDocument() {
		XPathQuery query = new XPathQuery();
		DefaultMutableTreeNode treeResult = query.loadXPathQuery();
		TreeToTableFunctor functor = new TreeToTableFunctor();
		HashSet<String> primaryKeys = new HashSet<>();
		primaryKeys.add("OrderId");
		Table[] tableResult = functor.runFunctor(treeResult, primaryKeys);
		TreeToGraph treetograph = new TreeToGraph(treeResult);
		DefaultDirectedGraph graphResult = treetograph.getDirectedGraph();
		return new JSONrepository(tableResult, treeResult, graphResult);
	}
}