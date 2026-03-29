package program.gui.graphic.rectangle;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.graphic.IGraphic;

public interface IRectangle extends IGraphic {
    float[] getSize();
    void setSize(float width, float height);

    float[] getArcSize();
    void setArcSize(float arcWidth, float arcHeight);

    Box getBox();
}
