package program.gui.graphic.rectangle.rectangles;

import program.gui.graphic.rectangle.AbstractRectangle;

import java.awt.*;

public class BasicRectangle extends AbstractRectangle {
    public BasicRectangle () {

    }

    public BasicRectangle (float x, float y, float width, float height, float arcWidth, float arcHeight, Color color) {
        super(x, y, width, height, arcWidth, arcHeight, color);
    }

    @Override
    public void draw(Graphics g) {
        setColor(g);
        drawRect(g);
    }

    @Override
    public void fill(Graphics g) {
        setColor(g);
        fillRect(g);
    }
}
