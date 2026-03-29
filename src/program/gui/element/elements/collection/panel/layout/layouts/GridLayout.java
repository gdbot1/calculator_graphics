package program.gui.element.elements.collection.panel.layout.layouts;

import program.gui.element.IElement;
import program.gui.element.elements.collection.panel.IPanel;
import program.gui.element.elements.collection.panel.layout.ILayout;

import java.util.List;

public class GridLayout implements ILayout {
    protected int rows, columns;

    public GridLayout(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void layoutElements(IPanel panel) {
        float[] panelSize = panel.getSize();

        float elementWidth = panelSize[0] / columns, elementHeight = panelSize[1] / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int elementId = i * columns + j;

                if (elementId >= panel.size()) {
                    return;
                }

                panel.get(elementId).setPosition(elementWidth * j, elementHeight * i);
                panel.get(elementId).setSize(elementWidth + 1, elementHeight + 1);
            }
        }
    }
}
