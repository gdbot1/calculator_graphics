package program.gui.utils;

import program.gui.element.IElement;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;

public class ElementUtils {
    public static void pullElementOnBox(IElement element, Box box) {
        element.setPosition(box.getX(), box.getY());
        element.setSize(box.getWidth(), box.getHeight());
    }
}
