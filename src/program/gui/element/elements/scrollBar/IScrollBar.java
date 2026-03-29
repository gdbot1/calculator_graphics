package program.gui.element.elements.scrollBar;

import program.gui.element.IElement;
import program.gui.element.listener.ElementMouseListener;

public interface IScrollBar extends IElement, ElementMouseListener {
    void setScroll(float scroll);
    float getScroll();

    void setScrollButtonSize(float scrollButtonSize);
    float getScrollButtonSize();

    void setVertical(boolean vertical);
    boolean isVertical();

    boolean isPressed();
}
