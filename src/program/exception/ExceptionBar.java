package program.exception;

import program.gui.alignment.Alignment;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.utils.FontUtils;

import java.awt.*;
import java.io.IOException;

public class ExceptionBar extends IOException {
    protected long time, beginTime = System.nanoTime();
    protected Alignment alignment;
    protected Color color;

    public ExceptionBar(String message, Color color, Alignment alignment, long time) {
        super(message);

        this.color = color;
        this.alignment = alignment;
        this.time = time;
    }

    public ExceptionBar(IOException exception, Color color, Alignment alignment, long time) {
        super(exception);

        this.color = color;
        this.alignment = alignment;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics g, Box ranges) {
        if (ranges == null) {
            return;
        }

        String s = this.getMessage();

        Font font = new Font("", Font.PLAIN, 14);

        float textWidth = FontUtils.getTextWidth(s, font), textHeight = font.getSize();

        float[] position = alignment.getAlignmentPosition(ranges.getX(), ranges.getY(), ranges.getWidth(), ranges.getHeight(), textWidth, textHeight, true);

        int alpha = (int)((1 - Math.clamp((float)(System.nanoTime() - beginTime) / time, 0, 1)) * 255);

        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));

        g.setFont(font);

        g.drawString(s, (int)position[0], (int)(position[1]));
    }

    public boolean isInactive() {
        return System.nanoTime() - beginTime > time;
    }
}
