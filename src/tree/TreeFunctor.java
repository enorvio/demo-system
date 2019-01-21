package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.tree.DefaultMutableTreeNode;
import relationalCategory.Row;
import relationalCategory.Table;

public class TreeFunctor {

    public Row subtreeToRow(String[] attributes, DefaultMutableTreeNode node) {
        int l = attributes.length;
        String[] row = new String[l];
        ArrayList<DefaultMutableTreeNode> children = Collections.list(node.children());
        System.out.println(children.size());
        if (children.size() == 1) {
            return subtreeToRow(attributes, children.get(0));
        } else {
            for (int i = 0; i < children.size(); i++) {
                if (!children.isEmpty()) {
                    if (children.get(i).getUserObject() instanceof String[]) {
                        String[] value = (String[]) children.get(i).getUserObject();
                        row[i] = value[1];
                    } else if (children.get(i).getUserObject() instanceof String) {
                        String value = (String) children.get(i).getUserObject();
                        row[i] = value;
                    }
                }
            }
            Row record = new Row(attributes, row);
            return record;
        }
    }

    public Table subtreeToTable(String[] attributes, DefaultMutableTreeNode rootNode) {
        Table subtreeTable = new Table(" ", attributes);
        ArrayList<DefaultMutableTreeNode> children = Collections.list(rootNode.children());
        for (DefaultMutableTreeNode child : children) {
            Row record = subtreeToRow(attributes, child);
            subtreeTable.addRow(record);
        }
        return subtreeTable;
    }
    
    public Table[] treeToTables(DefaultMutableTreeNode rootNode) {
        ArrayList<Table> tables = new ArrayList<>();
        ArrayList<DefaultMutableTreeNode> children = Collections.list(rootNode.children());
        if (children.size() == 1) {
            return treeToTables(children.get(0));
        } else {
            ArrayList<String> row = new ArrayList<>();
            ArrayList<String> attributes = new ArrayList<>();
            for (DefaultMutableTreeNode child :  children) {
                if(getAttributeAndDataFromChild(child) != null) {
                    String[] attributeAndData = getAttributeAndDataFromChild(child);
                    attributes.add(attributeAndData[0]);
                    row.add(attributeAndData[1]);
                } else {
                    Table[] brancTables = treeToTables(child);
                    tables.addAll(Arrays.asList(brancTables));
                }
            }
            
            String[] rowList = row.toArray(new String[row.size()]);
            String[] attributeList = attributes.toArray(new String[row.size()]);
            Row tabelRow = new Row(attributeList, rowList);
            Table table = new Table(rootNode.toString(),attributeList);
            table.addRow(tabelRow);
            tables.add(table);
        }
        Table[] tableList = tables.toArray(new Table[tables.size()]);
        return tableList;
    }
    
    public String[] getAttributeAndDataFromChild(DefaultMutableTreeNode childNode) {
        ArrayList<DefaultMutableTreeNode> children = Collections.list(childNode.children());
        if(children.size() == 1 && children.get(0).getUserObject() instanceof String) {
            String[] attributeAndData = {childNode.toString(), (String) children.get(0).getUserObject()};
                return attributeAndData;
        }
        return null;
    }

}
