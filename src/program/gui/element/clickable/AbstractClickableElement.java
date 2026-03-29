package program.gui.element.clickable;

import program.gui.element.AbstractElement;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.listener.ElementMouseListener;
import program.gui.utils.MouseUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractClickableElement extends AbstractElement implements ElementMouseListener {
    protected boolean mouseInside;
    protected Point mouse_position;

    public AbstractClickableElement() {

    }

    public AbstractClickableElement(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void update (JFrame frame, Box rangeBox) {
        mouse_position = MouseUtils.getMousePosition(frame);

        Box newRangeBox = Box.getIntersection(getBox(), rangeBox);

        mouseInside = MouseUtils.checkCollision(mouse_position, newRangeBox);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        this.mouseInside = false;
    }

    public abstract void onClick(MouseEvent e);

    @Override
    public boolean mousePressed(MouseEvent e) {
        if (mouseInside) {
            onClick(e);

            return true;
        }

        return false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public boolean isMouseInside() {
        return mouseInside;
    }
}