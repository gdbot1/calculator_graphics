package program.gui.graphic.rectangle;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.graphic.AbstractGraphic;

import java.awt.*;

public abstract class AbstractRectangle extends AbstractGraphic implements IRectangle {
    protected float width, height, arcWidth, arcHeight;

    public AbstractRectangle () {

    }

    public AbstractRectangle (float x, float y, float width, float height, float arcWidth, float arcHeight, Color color) {
        super(x, y, color);

        this.width = width;
        this.height = height;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    public float[] getSize() {
        return new float[]{width, height};
    }

    @Override
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public float[] getArcSize() {
        return new float[]{arcWidth, arcHeight};
    }

    @Override
    public void setArcSize(float arcWidth, float arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    protected float[] getArcSizeInPixels() {
        return new float[]{arcWidth * width, arcHeight * height};
    }

    protected void setColor(Graphics g) {
        g.setColor(color);
    }

    @Override
    public Box getBox() {
        return new Box(x, y, width, height);
    }

    protected void drawRect(Graphics g) {
        float[] arcSize = getArcSizeInPixels();

        g.drawRoundRect((int)x, (int)y, (int)width, (int)height, (int)arcSize[0], (int)arcSize[1]);
    }

    protected void fillRect(Graphics g) {
        float[] arcSize = getArcSizeInPixels();

        g.fillRoundRect((int)x, (int)y, (int)width, (int)height, (int)arcSize[0], (int)arcSize[1]);
    }
}
