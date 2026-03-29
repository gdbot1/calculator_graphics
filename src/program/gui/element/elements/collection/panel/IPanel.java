package program.gui.element.elements.collection.panel;

import program.gui.element.IElement;
import program.gui.element.elements.collection.ICollection;
import program.gui.element.listener.ElementKeyListener;
import program.gui.element.listener.ElementMouseListener;
import program.gui.element.listener.ElementMouseWheelListener;

public interface IPanel extends IElement, ICollection<IElement>, ElementKeyListener, ElementMouseListener, ElementMouseWheelListener {
    void add(IElement element);

    void layoutElements();
}
