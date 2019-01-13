package treeCategory;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import relationalCategory.*;

public class SubtreeCategory {
	private DefaultMutableTreeNode supertree;
	private ArrayList<DefaultMutableTreeNode> subtrees;
	private ArrayList<DefaultMutableTreeNode[]> partialOrder;

	public SubtreeCategory(DefaultMutableTreeNode supertree) {
		this.supertree = supertree;
		this.subtrees = new ArrayList<DefaultMutableTreeNode>();
		this.partialOrder = new ArrayList<DefaultMutableTreeNode[]>();
	}
	
	public Table treeToTableFunctor(DefaultMutableTreeNode nNode) {
		return null;
	}
	
	public void addTree(DefaultMutableTreeNode subtree) {
		this.subtrees.add(subtree);
		DefaultMutableTreeNode[] defaultPair = {subtree, this.supertree};
		this.partialOrder.add(defaultPair);
	}
	
	public void addPair(DefaultMutableTreeNode tree1, DefaultMutableTreeNode tree2) {
		DefaultMutableTreeNode[] pair = {tree1, tree2};
		this.partialOrder.add(pair);
	}
	
	public Integer compareTrees(DefaultMutableTreeNode tree1, DefaultMutableTreeNode tree2) {
		DefaultMutableTreeNode[] pair1 = {tree1, tree2};
		DefaultMutableTreeNode[] pair2 = {tree2, tree1};
		if(this.partialOrder.contains(pair1)) {
			if(this.partialOrder.contains(pair2)) {
				return 0;
			}
			return -1;
		} else if (this.partialOrder.contains(pair2)) {
			return 1;
		}
		return null;
	}
	
}


