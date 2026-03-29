package program.gui.element.elements.button;

import program.gui.element.IElement;
import program.gui.element.listener.ButtonListener;
import program.gui.element.listener.ElementMouseListener;


public interface IButton extends IElement, ElementMouseListener {
    void setListener(ButtonListener listener);
    ButtonListener getListener();
}
