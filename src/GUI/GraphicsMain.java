package GUI;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GraphicsMain {

    public GraphicsMain() {
        JFrame window = new JFrame();
        window.setSize(1024, 350);
        window.setTitle("Category theory on multi-model databases");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DemoQueryDefinitions queries = new DemoQueryDefinitions();

        JLabel label = new JLabel("Category Theory on Multi-model Databases");
        label.setFont(new Font("Arial", Font.PLAIN, 32));

        QueryButtons QB = new QueryButtons();
        ButtonComponent BC = new ButtonComponent(QB);
        QB.setVisible(false);

        BoxLayoutContainer container = new BoxLayoutContainer();
        container.addComponentsToPane(label, window.getContentPane());
        container.addComponentsToPane(queries, window.getContentPane());
        container.addComponentsToPane(BC, window.getContentPane());
        container.addComponentsToPane(QB, window.getContentPane());

        window.setVisible(true);
    }

}
