package program.gui.element.elements.button;

import program.gui.element.clickable.AbstractClickableElement;
import program.gui.element.listener.ButtonListener;

import java.awt.event.MouseEvent;

public abstract class AbstractButton extends AbstractClickableElement implements IButton {
    protected ButtonListener listener;

    public AbstractButton() {

    }

    public AbstractButton(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void setListener(ButtonListener listener) {
        this.listener = listener;
    }

    @Override
    public ButtonListener getListener() {
        return listener;
    }

    @Override
    public void onClick(MouseEvent e) {
        focused = true;

        if (listener != null) {
            listener.onButtonPressed(this);
        }
    }

    public void destroy() {
        mouseInside = false;
    }
}
