package dataViewers;

import javax.swing.*;
import java.awt.*;
import javax.swing.tree.*;
import xmlReader.XMLReader;

public class TreeViewer {

    private XMLReader reader;
    private String filename;
    private JFrame frame;
    private JScrollPane layer;

    public TreeViewer(String filename) {
        this.filename = filename;
        this.reader = new XMLReader(filename);
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
        JTree tree = new JTree(treeNode);
        this.layer = new JScrollPane(tree);
    }
    
    public JScrollPane getGraphicTree() {
        return this.layer;
    }
}
