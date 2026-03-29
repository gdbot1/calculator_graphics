package program.gui.element.elements.button.buttons.textButtons;

import program.gui.element.elements.button.buttons.AbstractTextButton;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.graphic.rectangle.rectangles.SelectedBorder;
import program.gui.graphic.rectangle.rectangles.SelectedRectangle;
import program.gui.alignment.Alignment;
import program.gui.graphic.text.texts.SelectedTextBox;
import program.gui.utils.GraphicsUtils;

import java.awt.*;

public class TextButton extends AbstractTextButton {
    public TextButton (){

    }

    public TextButton(float x, float y, float width, float height, String text) {
        SelectedRectangle background = new SelectedRectangle(x, y, width, height, 0, 0, false, 20, new Color(180, 185, 190));
        SelectedTextBox textBox = new SelectedTextBox(x, y, width, height, 0.5f, 0, false, Alignment.CENTER, Color.black, text, new Font("", Font.PLAIN, 20), true, true);
        SelectedBorder border = new SelectedBorder(x, y, width, height, 0, 0, 0.05f, 20, new Color(160, 165, 170));

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.background = background;
        this.border = border;
        this.textBox = textBox;
    }

    public TextButton(float x, float y, float width, float height, float arcWidth, float arcHeight, float textSize, float borderWidth, Color backgroundColor, Color borderColor, Color textColor, Alignment alignment, Font font, String text, int change) {
        SelectedRectangle background = new SelectedRectangle(x, y, width, height, arcWidth, arcHeight, false, change, backgroundColor);
        SelectedTextBox textBox = new SelectedTextBox(x, y, width, height, textSize, change, false, alignment, textColor, text, font, true ,true);
        SelectedBorder border = new SelectedBorder(x, y, width, height, arcWidth, arcHeight, borderWidth, change, borderColor);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.background = background;
        this.border = border;
        this.textBox = textBox;
    }

    public TextButton(float x, float y, float width, float height, SelectedRectangle background, SelectedBorder border, SelectedTextBox text) {
        super(x, y, width, height, background, border, text);
    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        Graphics2D g2D = (Graphics2D) g;

        float[] global_position = getGlobalPosition();

        GraphicsUtils.setClip(g2D, this, rangeBox);

        drawBackground(g);

        drawTextBox(g);

        drawBorder(g);

        if (focused) {
            g.setColor(border.getColor().darker());
            g.drawRect((int) global_position[0], (int) global_position[1], (int) width, (int) height);
        }

        GraphicsUtils.disableClip(g2D);
    }
}
