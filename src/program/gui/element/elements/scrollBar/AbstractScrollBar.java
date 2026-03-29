package program.gui.element.elements.scrollBar;

import program.gui.element.clickable.AbstractClickableElement;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractScrollBar extends AbstractClickableElement implements IScrollBar {
    protected boolean mousePressed, vertical;
    protected float scroll = 0, scrollButtonSize;

    public AbstractScrollBar() {

    }

    public AbstractScrollBar(float x, float y, float width, float height, float scrollButtonSize, boolean vertical) {
        super(x, y, width, height);

        this.scrollButtonSize = scrollButtonSize;
        this.vertical = vertical;
    }

    @Override
    public void setScroll(float scroll) {
        this.scroll = scroll;
    }

    @Override
    public float getScroll() {
        return scroll;
    }

    @Override
    public void setScrollButtonSize(float scrollButtonSize) {
        this.scrollButtonSize = scrollButtonSize;
    }

    @Override
    public float getScrollButtonSize() {
        return scrollButtonSize;
    }

    @Override
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    @Override
    public boolean isVertical() {
        return vertical;
    }

    @Override
    public boolean isPressed() {
        return mousePressed;
    }

    public void updateScrollButtonHeight(float listHeight) {
        scrollButtonSize = Math.clamp(height / listHeight, 0, 1);
    }

    protected float getScrollButtonHeightInPixels() {
        return scrollButtonSize * height;
    }

    protected float getScrollFieldHeight() {
        return height - getScrollButtonHeightInPixels();
    }

    protected float getScrollButtonWidthInPixels() {
        return scrollButtonSize * width;
    }

    protected float getScrollFieldWidth() {
        return width - getScrollButtonWidthInPixels();
    }

    protected float findScrollX(Point mouse_position) {
        float[] global_position = getGlobalPosition();

        return Math.clamp((mouse_position.x - global_position[0] - getScrollButtonWidthInPixels() / 2) / getScrollFieldWidth(), 0, 1);
    }

    protected float findScrollY(Point mouse_position) {
        float[] global_position = getGlobalPosition();

        return Math.clamp((mouse_position.y - global_position[1] - getScrollButtonHeightInPixels() / 2) / getScrollFieldHeight(), 0, 1);
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {
        super.update(frame, rangeBox);

        if (mousePressed) {
            scroll = vertical ? findScrollY(mouse_position) : findScrollX(mouse_position);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;

        super.mouseReleased(e);
    }
}
