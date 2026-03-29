package program.gui.element.elements.collection.panel.layout.layouts;

import program.gui.element.elements.collection.panel.IPanel;
import program.gui.element.elements.collection.panel.layout.ILayout;

public class ListLayout implements ILayout {
    protected float scroll, boxHeight;

    public ListLayout(float scroll, float boxHeight) {
        this.scroll = scroll;
        this.boxHeight = boxHeight;
    }

    @Override
    public void layoutElements(IPanel panel) {
        float[] panelSize = panel.getSize();
        float boxHeightInPixels = getBoxHeightInPixels(panel);

        float boxesHeight = boxHeightInPixels * panel.size();

        for (int i = 0; i < panel.size(); i++) {
            panel.get(i).setPosition(0, i * boxHeightInPixels - scroll * Math.max(0, boxesHeight - panelSize[1]));
            panel.get(i).setSize(panelSize[0], boxHeightInPixels);
        }
    }

    public float getScroll() {
        return scroll;
    }

    public void setScroll(float scroll) {
        this.scroll = scroll;
    }

    public float getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(float boxHeight) {
        this.boxHeight = boxHeight;
    }

    public float getBoxHeightInPixels(IPanel panel) {
        float[] panelSize = panel.getSize();

        return boxHeight * panelSize[0];
    }

    public float getListHeight(IPanel panel) {
        float boxHeightInPixels = getBoxHeightInPixels(panel);

        return boxHeightInPixels * panel.size();
    }
}
