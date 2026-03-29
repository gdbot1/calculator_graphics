package program.gui.utils;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;

import javax.swing.*;

public class FrameUtils {
    public static Box getBox(JFrame frame) {
        return new Box(0, 0, frame.getWidth() - 15, frame.getHeight() - 36);
    }
}
