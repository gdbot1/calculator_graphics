package program.gui.element.elements.collection.panel.panels;

import program.gui.element.elements.collection.panel.AbstractPanel;
import program.gui.element.elements.collection.panel.layout.ILayout;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.graphic.rectangle.IRectangle;
import program.gui.graphic.rectangle.rectangles.BasicRectangle;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Panel extends AbstractPanel {
    protected IRectangle background;

    public Panel() {

    }

    public Panel(float x, float y, float width, float height, ILayout layout) {
        super(x, y, width, height, layout);
    }

    public Panel(float x, float y, float width, float height, Color color, ILayout layout) {
        super(x, y, width, height, layout);

        this.background = new BasicRectangle(x, y, width, height, 0, 0,color);
    }

    @Override
    public void draw(Graphics g, Box box) {
        float[] position = getGlobalPosition();

        //g.setColor(new Color(0, 0, 0, 80));
        //g.fillRect((int)position[0], (int)position[1], (int)width, (int)height);

        if (background != null) {
            background.setPosition(position[0], position[1]);
            background.setSize(width, height);

            background.fill(g);
        }

        super.draw(g, box);
    }
}