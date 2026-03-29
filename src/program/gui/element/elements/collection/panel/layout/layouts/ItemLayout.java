package program.gui.element.elements.collection.panel.layout.layouts;

import program.gui.alignment.Alignment;
import program.gui.element.IElement;
import program.gui.element.elements.collection.panel.IPanel;
import program.gui.element.elements.collection.panel.layout.ILayout;

import java.util.List;

public class ItemLayout implements ILayout {
    protected float width, height;
    protected Alignment alignment;
    protected boolean changeTextWidthOnResize = true, changeTextHeightOnResize = true;

    public ItemLayout(float width, float height, Alignment alignment) {
        this.width = width;
        this.height = height;

        this.alignment = alignment;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setChangeTextWidthOnResize(boolean changeTextWidthOnResize) {
        this.changeTextWidthOnResize = changeTextWidthOnResize;
    }

    public void setChangeTextHeightOnResize(boolean changeTextHeightOnResize) {
        this.changeTextHeightOnResize = changeTextHeightOnResize;
    }

    public boolean isChangingTextWidthOnResize() {
        return changeTextWidthOnResize;
    }

    public boolean isChangingTextHeightOnResize() {
        return changeTextHeightOnResize;
    }

    @Override
    public void layoutElements(IPanel panel) {
        if (panel.size() == 0) {
            return;
        }

        IElement element = panel.get(0);

        float[] panelSize = panel.getSize();

        float scaledWidth = panelSize[1] * width / height, scaledHeight = panelSize[0] * height / width;

        if (changeTextWidthOnResize && changeTextHeightOnResize) {
            if (panelSize[0] / width < panelSize[1] / height) {
                element.setSize(panelSize[0], scaledHeight);
            } else {
                element.setSize(scaledWidth, panelSize[1]);
            }
        }
        else if (changeTextWidthOnResize) {
            element.setSize(panelSize[0], scaledHeight);
        }
        else if (changeTextHeightOnResize) {
            element.setSize(scaledWidth, panelSize[1]);
        }

        float[] elementSize = element.getSize();

        float[] elementPosition = alignment.getAlignmentPosition(0, 0, panelSize[0], panelSize[1], elementSize[0], elementSize[1], false);

        element.setPosition(elementPosition[0], elementPosition[1]);
    }
}