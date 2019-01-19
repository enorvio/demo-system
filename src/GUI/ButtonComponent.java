package GUI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class ButtonComponent extends JComponent implements ActionListener {

    private final JButton b1;
    private final JButton b2;
    private QueryButtons QB;

    public ButtonComponent(QueryButtons qb) {
        this.QB = qb;
        
        this.b1 = new JButton("Load data");
        this.b1.setFont(new Font("Arial", Font.PLAIN, 24));
        
        this.b2 = new JButton("Query processing");
        this.b2.setFont(new Font("Arial", Font.PLAIN, 24));
        
        setLayout(new FlowLayout());
        
        add(this.b1);
        add(this.b2);
        this.b1.addActionListener(this);
        this.b2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if(this.QB.isVisible()) {
                this.QB.setVisible(false);
            }
            System.out.println("Not supported yet.");
        } else if (e.getSource() == b2) {
            if(!this.QB.isVisible()) {
                this.QB.setVisible(true);
            }
        }
    }
}
