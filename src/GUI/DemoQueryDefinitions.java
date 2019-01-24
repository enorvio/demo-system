package GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DemoQueryDefinitions extends JComponent {

    private JLabel sqlLabel;
    private JLabel xmlLabel;
    private JLabel graphLabel;

    public DemoQueryDefinitions() {

        String sqlQuery = "(1) Select FirstName, LastName from Person where id = \"933\"";
        String xmlQuery = "(2) .//invoice[personId=\"10995116278711\"]/orderline";
        String graphQuery = "(3) MATCH (a:Person {name: 'Jennifer'})-[r:WORK_FOR]->(b:University)\n" +
            "RETURN a, r, b";

        this.sqlLabel = new JLabel(sqlQuery);
        this.xmlLabel = new JLabel(xmlQuery);
        this.graphLabel = new JLabel(graphQuery);
        setLayout(new FlowLayout());
        sqlLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        xmlLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        graphLabel.setFont(new Font("Courier New", Font.PLAIN, 20));

        add(sqlLabel);
        add(xmlLabel);
        add(graphLabel);
    }
}
