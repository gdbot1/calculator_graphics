package program.gui.element.elements.shape.shapes;

import program.gui.element.AbstractElement;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.shape.IShape;
import program.gui.graphic.IGraphic;
import program.gui.graphic.rectangle.IRectangle;
import program.gui.graphic.text.texts.TextBox;
import program.gui.utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;

public class TextBoxShape extends AbstractElement implements IShape {
    protected TextBox textBox;

    public TextBoxShape(TextBox textBox) {
        this.textBox = textBox;
    }

    @Override
    public IGraphic getGraphicShape() {
        return textBox;
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {

    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        Graphics2D g2D = (Graphics2D) g;

        Box newRangeBox = Box.getIntersection(getBox(), rangeBox);

        float[] position = getGlobalPosition();

        textBox.setPosition(position[0], position[1]);
        textBox.setSize(width, height);

        GraphicsUtils.setClip(g2D, newRangeBox);

        textBox.draw(g);

        GraphicsUtils.disableClip(g2D);
    }
}
