package treeCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import javax.swing.tree.DefaultMutableTreeNode;
import relationalCategory.Row;
import relationalCategory.Table;

public class TreeToTableFunctor {

    public Table[] runFunctor(DefaultMutableTreeNode node, HashSet<String> primaryKeys) {
        createPrimaryForeignKeys(node, primaryKeys);
        Table[] tableResult = treeToTables(node);
        Table[] mergedResult = mergeTables(tableResult);
        return mergedResult;
    }

    private Table[] treeToTables(DefaultMutableTreeNode rootNode) {
        ArrayList<Table> tables = new ArrayList<>();
        ArrayList<DefaultMutableTreeNode> children = Collections.list(rootNode.children());
        if (children.size() == 1) {
            return treeToTables(children.get(0));
        } else {
            ArrayList<String> row = new ArrayList<>();
            ArrayList<String> attributes = new ArrayList<>();
            for (DefaultMutableTreeNode child : children) {
                if (getAttributeAndDataFromChild(child) != null) {
                    String[] attributeAndData = getAttributeAndDataFromChild(child);
                    attributes.add(attributeAndData[0]);
                    row.add(attributeAndData[1]);
                } else {
                    Table[] brancTables = treeToTables(child);
                    tables.addAll(Arrays.asList(brancTables));
                }
            }
            String[] rowList = row.toArray(new String[0]);
            String[] attributeList = attributes.toArray(new String[0]);
            Row tabelRow = new Row(attributeList, rowList);
            Table table = new Table(rootNode.toString(), attributeList);
            table.addRow(tabelRow);
            tables.add(table);
        }
        Table[] tableList = tables.toArray(new Table[0]);
        return tableList;
    }

    private String[] getAttributeAndDataFromChild(DefaultMutableTreeNode childNode) {
        ArrayList<DefaultMutableTreeNode> children = Collections.list(childNode.children());
        if (children.size() == 1 && children.get(0).getUserObject() instanceof String) {
            String[] attributeAndData = {childNode.toString(), (String) children.get(0).getUserObject()};
            return attributeAndData;
        }
        return null;
    }

    private void createPrimaryForeignKeys(DefaultMutableTreeNode rootNode, HashSet<String> primaryKeys) {
        if (!rootNode.isLeaf()) {
            ArrayList<DefaultMutableTreeNode> children = Collections.list(rootNode.children());
            for (DefaultMutableTreeNode child : children) {
                createPrimaryForeignKeys(child, primaryKeys);
                if (primaryKeys.contains(child.toString())) {
                    for (DefaultMutableTreeNode child1 : children) {
                        ArrayList<DefaultMutableTreeNode> childChildren = Collections.list(child1.children());
                        if (childChildren.size() > 1) {
                            DefaultMutableTreeNode primaryKeyNode = new DefaultMutableTreeNode(child.getUserObject());
                            DefaultMutableTreeNode primaryDataNode = new DefaultMutableTreeNode(child.children().nextElement().toString());
                            primaryKeyNode.add(primaryDataNode);
                            child1.add(primaryKeyNode);
                        }
                    }
                }
            }
        }
    }

    private Table[] mergeTables(Table[] inputTables) {
        ArrayList<Table> tables = new ArrayList<>();
        HashSet<Integer> usedRows = new HashSet<>();
        int nextIterationStart = 0;
        int times = 0;
        for (int i = 0; i < inputTables.length; i++) {
            for (int j = i + 1; j < inputTables.length; j++) {
                boolean success = mergeTwoTables(inputTables[i], inputTables[j]);
                if (success) {
                    usedRows.add(j);
                } else {
                    if ((times == 0 && nextIterationStart == 0) || (times == 0 && !usedRows.contains(j))) {
                        nextIterationStart = j;
                        times++;
                    }
                }
            }
            tables.add(inputTables[i]);
            if (i == inputTables.length - 1) {
                break;
            }
            i = nextIterationStart - 1;
            times = 0;
        }
        Table[] tableList = tables.toArray(new Table[0]);
        return tableList;
    }

    private boolean mergeTwoTables(Table table1, Table table2) {
        if (Arrays.equals(table1.getAttributes(), table2.getAttributes())) {
            table1.addRows(table2.getRows());
            return true;
        }
        return false;
    }
}
