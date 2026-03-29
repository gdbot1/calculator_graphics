package program.gui.element.elements.shape.shapes;

import program.gui.element.AbstractElement;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.shape.IShape;
import program.gui.graphic.IGraphic;
import program.gui.graphic.rectangle.IRectangle;
import program.gui.utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;

public class RectangleShape extends AbstractElement implements IShape {
    protected IRectangle rectangle;

    public RectangleShape(IRectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public IGraphic getGraphicShape() {
        return rectangle;
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {

    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        Graphics2D g2D = (Graphics2D) g;

        Box newRangeBox = Box.getIntersection(getBox(), rangeBox);

        float[] position = getGlobalPosition();

        rectangle.setPosition(position[0], position[1]);
        rectangle.setSize(width, height);

        GraphicsUtils.setClip(g2D, newRangeBox);

        rectangle.fill(g);

        GraphicsUtils.disableClip(g2D);
    }
}
