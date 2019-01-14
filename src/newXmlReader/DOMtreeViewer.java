package newXmlReader;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class DOMtreeViewer {

    private final DOMReader reader;

    public DOMtreeViewer(File file) {
        this.reader = new DOMReader(file);
        DefaultTreeModel tree = this.reader.getDOMtree();
        JFrame f = new JFrame();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getRoot();
        JTree jt = new JTree(root);

        DefaultMutableTreeNode lastLeaf = root.getLastLeaf();
        TreePath path = new TreePath(lastLeaf.getPath());
        jt.setSelectionPath(path);

        JScrollPane jsp = new JScrollPane(jt);
        Container c = f.getContentPane();
        c.add(jsp, BorderLayout.CENTER);
        f.setSize(500, 500);
        f.show();
    }
}
