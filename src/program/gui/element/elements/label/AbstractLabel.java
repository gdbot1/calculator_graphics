package program.gui.element.elements.label;

import program.gui.alignment.Alignment;
import program.gui.element.AbstractElement;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.graphic.rectangle.rectangles.BasicRectangle;
import program.gui.graphic.text.texts.TextBox;
import program.gui.utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractLabel extends AbstractElement implements ILabel {
    protected TextBox textBox;
    protected BasicRectangle background;

    protected String text;

    public AbstractLabel() {

    }

    public AbstractLabel(float x, float y, float width, float height, TextBox textBox, BasicRectangle background) {
        super(x, y, width, height);

        this.textBox = textBox;
        this.background = background;
    }

    public AbstractLabel(float x, float y, float width, float height, String text) {
        super(x, y, width, height);

        this.textBox = new TextBox(x, y, width, height, 0.75f, Alignment.CENTER, Color.black, text, new Font("", Font.PLAIN, 20), true, true);
        this.background = new BasicRectangle(x, y, width, height, 0, 0, new Color(150, 155, 160));
    }

    public TextBox getTextBox() {
        return textBox;
    }

    public void setTextBox(TextBox textBox) {
        this.textBox = textBox;
    }

    public BasicRectangle getBackground() {
        return background;
    }

    public void setBackground(BasicRectangle background) {
        this.background = background;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {

    }

    public void drawBackground(Graphics g) {
        float[] global_position = getGlobalPosition();

        background.setPosition(global_position[0], global_position[1]);
        background.setSize(width, height);

        background.draw(g);
    }

    public void drawTextField(Graphics g) {
        float[] global_position = getGlobalPosition();

        textBox.setPosition(global_position[0], global_position[1]);
        textBox.setSize(width, height);
        textBox.setText(text);

        textBox.draw(g);
    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        Graphics2D g2D = (Graphics2D) g;

        Box newRangeBox = Box.getIntersection(getBox(), rangeBox);

        GraphicsUtils.setClip(g2D, newRangeBox);

        GraphicsUtils.disableClip(g2D);
    }
}

