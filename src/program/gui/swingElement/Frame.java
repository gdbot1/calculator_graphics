package program.gui.swingElement;

import program.gui.GUI;
import program.gui.element.IElement;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.utils.ElementUtils;
import program.gui.utils.FrameUtils;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(int width, int height, String title) {
        setSize(width, height);
        setTitle(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Box getBox() {
        return FrameUtils.getBox(this);
    }

    public void connectGui(GUI gui) {
        addKeyListener(gui);
        addMouseListener(gui);
        addMouseWheelListener(gui);
    }

    public void pullElement(IElement element) {
        ElementUtils.pullElementOnBox(element, getBox());
    }
}
