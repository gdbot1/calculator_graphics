package program.gui.element.elements.collection.panel.layout.layouts.boxLayout;

import program.gui.element.elements.collection.panel.IPanel;
import program.gui.element.elements.collection.panel.layout.ILayout;

import java.util.Arrays;
import java.util.List;

public class BoxLayout implements ILayout {
    public List<Box> boxes;

    public BoxLayout(List<Box> boxes) {
        this.boxes = boxes;
    }

    public BoxLayout(Box[] boxes) {
        this.boxes = Arrays.asList(boxes);
    }

    @Override
    public void layoutElements(IPanel panel) {
        float[] panelSize = panel.getSize();

        for (int i = 0; i < boxes.size(); i++) {
            if (i >= panel.size()) {
                return;
            }

            Box box = boxes.get(i);

            panel.get(i).setPosition(box.getX() * panelSize[0], box.getY() * panelSize[1]);
            panel.get(i).setSize(box.getWidth() * panelSize[0], box.getHeight() * panelSize[1]);
        }
    }
}
