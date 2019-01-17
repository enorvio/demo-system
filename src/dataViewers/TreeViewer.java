package dataViewers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.tree.*;
import newXmlReader.NewXMLReader;

public class TreeViewer {

    private NewXMLReader reader;
    private String filename;
    private JFrame frame;

    public TreeViewer(String filename) {
        this.filename = filename;
        this.reader = new NewXMLReader(filename);
        this.frame = new JFrame("Tree View:" + filename);
        frame.setSize(800, 800);
        frame.getContentPane().setLayout(new BorderLayout());
        DefaultMutableTreeNode rootNode = this.reader.getTree();
        JTree tree = new JTree(rootNode);
        JScrollPane scrollPane = new JScrollPane(tree);
        frame.getContentPane().add("Center", scrollPane);
        frame.setVisible(true);
    }

    public TreeViewer(DefaultMutableTreeNode treeNode) {
        this.frame = new JFrame("Tree View: " + treeNode.toString());
        frame.setSize(800, 800);
        frame.getContentPane().setLayout(new BorderLayout());
        JTree tree = new JTree(treeNode);
        //printTree(treeNode, 0);
        JScrollPane scrollPane = new JScrollPane(tree);
        frame.getContentPane().add("Center", scrollPane);
        frame.setVisible(true);
    }

    private void printTree(DefaultMutableTreeNode root, int i) {
        System.out.println(String.valueOf(i) + " " + root.toString());
        ArrayList<DefaultMutableTreeNode> children = Collections.list(root.children());
        i++;
        for (DefaultMutableTreeNode child : children) {
            printTree(child, i);
        }
    }
}
