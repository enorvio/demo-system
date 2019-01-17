
package GUI;

import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class BoxLayoutContainer {
    public void addComponentsToPane(JComponent component, Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(component);
    }
}
