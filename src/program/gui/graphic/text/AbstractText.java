package program.gui.graphic.text;

import program.gui.graphic.AbstractGraphic;
import program.gui.utils.FontUtils;

import java.awt.*;

public abstract class AbstractText extends AbstractGraphic implements IText {
    protected String text;
    protected Font font;

    public AbstractText () {

    }

    public AbstractText (float x, float y, Color color, String text, Font font) {
        super(x, y, color);

        this.text = text;
        this.font = font;
    }

    public void setCenterPosition(float x, float y) {
        if (font == null || text == null) {
            return;
        }

        float[] textBoxSize = getSizeBox();

        this.x = x - textBoxSize[0]/2;
        this.y = y + textBoxSize[1]/2;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public float[] getSizeBox() {
        return new float[]{FontUtils.getTextWidth(text, font), font.getSize() * 0.75f};
    }

    protected void setColor(Graphics g) {
        g.setColor(color);
    }

    protected void setFont(Graphics g) {
        g.setFont(font);
    }

    protected void drawText(Graphics g) {
        g.drawString(text, (int)x, (int)y);
    }
}
