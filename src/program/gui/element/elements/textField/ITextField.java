package program.gui.element.elements.textField;

import program.gui.element.IElement;
import program.gui.element.listener.ElementKeyListener;
import program.gui.element.listener.ElementMouseListener;

import java.awt.event.KeyListener;

public interface ITextField extends IElement, ElementMouseListener, ElementKeyListener {
    String getText();
    void setText(String text);

    int[] getCursor();
    void setCursor(int from, int to);
}
