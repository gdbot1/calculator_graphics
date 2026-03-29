package program.gui.element.elements.button.buttons;

import program.gui.element.elements.button.AbstractButton;
import program.gui.graphic.rectangle.rectangles.SelectedBorder;
import program.gui.graphic.rectangle.rectangles.SelectedRectangle;
import program.gui.graphic.text.texts.SelectedTextBox;

import java.awt.*;

public abstract class AbstractTextButton extends AbstractButton {
    protected SelectedRectangle background;
    protected SelectedBorder border;
    protected SelectedTextBox textBox;

    public AbstractTextButton () {

    }

    public AbstractTextButton(float x, float y, float width, float height, SelectedRectangle background, SelectedBorder border, SelectedTextBox text) {
        super(x, y, width, height);

        this.background = background;
        this.border = border;
        this.textBox = text;
    }

    public String getText() {
        return textBox.getText();
    }

    public SelectedTextBox getTextBox() {
        return textBox;
    }

    public void setTextBox(SelectedTextBox textBox) {
        this.textBox = textBox;
    }

    public SelectedRectangle getBackground() {
        return background;
    }

    public void setBackground(SelectedRectangle background) {
        this.background = background;
    }

    public SelectedBorder getBorder() {
        return border;
    }

    public void setBorder(SelectedBorder border) {
        this.border = border;
    }

    public void drawBackground(Graphics g) {
        float[] global_position = getGlobalPosition();

        background.setPosition(global_position[0], global_position[1]);
        background.setSize(width, height);

        background.setSelected(mouseInside);

        background.fill(g);
    }

    public void drawTextBox(Graphics g) {
        float[] global_position = getGlobalPosition();

        textBox.setPosition(global_position[0], global_position[1]);
        textBox.setSize(width, height);

        textBox.draw(g);
    }

    public void drawBorder(Graphics g) {
        float[] global_position = getGlobalPosition();

        border.setPosition(global_position[0], global_position[1]);
        border.setSize(width, height);

        border.setSelected(mouseInside);

        border.draw(g);
    }
}
