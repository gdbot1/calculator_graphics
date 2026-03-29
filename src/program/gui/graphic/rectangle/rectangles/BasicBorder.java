package program.gui.graphic.rectangle.rectangles;

import program.gui.graphic.rectangle.AbstractRectangle;

import java.awt.*;

public class BasicBorder extends AbstractRectangle {
    protected float borderWidth;

    public BasicBorder() {

    }

    public BasicBorder(float x, float y, float width, float height, float arcWidth, float arcHeight, float borderWidth, Color color) {
        super(x, y, width, height, arcWidth, arcHeight, color);

        this.borderWidth = borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    private float getBorderWidthInPixels() {
        return borderWidth * Math.min(width, height);
    }

    public void applyBorderWidth(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setStroke(new BasicStroke((int)getBorderWidthInPixels()));
    }

    public void disableBorderWidth(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setStroke(new BasicStroke(1));
    }

    @Override
    public void drawRect(Graphics g) {
        float borderWidthInPixels = getBorderWidthInPixels();
        float[] arcSize = getArcSizeInPixels();

        g.drawRoundRect((int)(x + (int)(borderWidthInPixels / 2)), (int)(y + (int)(borderWidthInPixels / 2)), (int)(width - (int)borderWidthInPixels + 1), (int)(height - (int)borderWidthInPixels + 1), (int)Math.ceil(arcSize[0]), (int)Math.ceil(arcSize[1]));
    }

    @Override
    public void draw(Graphics g) {
        applyBorderWidth(g);
        setColor(g);
        drawRect(g);
        disableBorderWidth(g);
    }

    @Override
    public void fill(Graphics g) {
        draw(g);
    }
}
