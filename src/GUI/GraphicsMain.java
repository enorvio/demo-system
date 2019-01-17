package GUI;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GraphicsMain {

    public GraphicsMain() {
        JFrame window = new JFrame();
        window.setSize(1024, 250);
        window.setTitle("Category theory on multi-model databases");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Category theory on multi-model databases");
        label.setFont(new Font("Arial", Font.PLAIN, 32));

        QueryButtons QB = new QueryButtons();
        ButtonComponent BC = new ButtonComponent(QB);
        QB.setVisible(false);
        
        BoxLayoutContainer container = new BoxLayoutContainer();
        container.addComponentsToPane(label, window.getContentPane());
        container.addComponentsToPane(BC, window.getContentPane());
        container.addComponentsToPane(QB, window.getContentPane());
        
        window.setVisible(true);
    }

}