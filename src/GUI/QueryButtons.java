package GUI;

import queryButtonsLogic.GraphQueryButton;
import queryButtonsLogic.SQLQueryButton;
import queryButtonsLogic.XPathQueryButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

public class QueryButtons extends JComponent implements ActionListener {

    private final JButton sqlButton;
    private final JButton xmlButton;
    private final JButton graphButton;

    public QueryButtons() {
        this.sqlButton = new JButton("Execute SQL query (1)");
        this.xmlButton = new JButton("Execute XPath query (2)");
        this.graphButton = new JButton("Execute Graph query (3)");
        setLayout(new FlowLayout());
        this.sqlButton.setFont(new Font("Arial", Font.PLAIN, 18));
        this.xmlButton.setFont(new Font("Arial", Font.PLAIN, 18));
        this.graphButton.setFont(new Font("Arial", Font.PLAIN, 18));

        add(this.sqlButton);
        add(this.xmlButton);
        add(this.graphButton);

        this.sqlButton.addActionListener(this);
        this.xmlButton.addActionListener(this);
        this.graphButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.sqlButton) {
            SQLQueryButton sqlbutton = new SQLQueryButton();
            sqlbutton.execute();
        } else if (e.getSource() == this.xmlButton) {
            XPathQueryButton xpathbutton = new XPathQueryButton();
            xpathbutton.execute();
        } else if (e.getSource() == this.graphButton) {
            GraphQueryButton graphbutton = new GraphQueryButton();
            graphbutton.execute();
        }
    }
}
