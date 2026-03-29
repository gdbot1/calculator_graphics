package program.gui.graphic.text.texts;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.graphic.text.AbstractText;
import program.gui.alignment.Alignment;
import program.gui.utils.FontUtils;

import java.awt.*;

public class TextBox extends AbstractText {
    protected float width, height;
    protected float textSize;
    protected Alignment alignment;
    boolean changeTextWidthOnResize, changeTextHeightOnResize;

    public TextBox () {

    }

    public TextBox (float x, float y, float width, float height, float textSize, Alignment alignment, Color color, String text, Font font, boolean changeTextWidthOnResize, boolean changeTextHeightOnResize) {
        super(x, y, color, text, font);

        this.textSize = textSize;
        this.alignment = alignment;
        this.changeTextWidthOnResize = changeTextWidthOnResize;
        this.changeTextHeightOnResize = changeTextHeightOnResize;

        setSize(width, height);
    }

    public void setSize(float width, float height) {
        Font temp = this.font.deriveFont(10.0f);

        float text_width_in_one_pixel = (float) FontUtils.getTextWidth(text, temp) / temp.getSize();
        int text_size = (int)(width / text_width_in_one_pixel);

        if (changeTextWidthOnResize && changeTextHeightOnResize) {
            this.font = this.font.deriveFont(Math.min(text_size, height) * textSize);
        }
        else if (changeTextWidthOnResize) {
            this.font = this.font.deriveFont(text_size * textSize);
        }
        else if (changeTextHeightOnResize) {
            this.font = this.font.deriveFont(height * textSize);
        }

        this.width = width;
        this.height = height;
    }

    public float[] getSize() {
        return new float[]{width, height};
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Alignment getTextAlignment() {
        return alignment;
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

    public Box getBox() {
        return new Box(x, y, width, height);
    }

    @Override
    public void drawText(Graphics g) {
        float[] sizeBox = getSizeBox();

        if (alignment != null) {
            float[] position = alignment.getAlignmentPosition(x, y, width, height, sizeBox[0], sizeBox[1], true);

            g.drawString(text, (int)position[0], (int)position[1]);
        }
    }

    @Override
    public void draw(Graphics g) {
        setFont(g);
        setColor(g);
        drawText(g);
    }

    @Override
    public void fill(Graphics g) {
        draw(g);
    }
}
