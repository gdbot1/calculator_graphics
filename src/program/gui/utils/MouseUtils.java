package program.gui.utils;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;

import javax.swing.*;
import java.awt.*;

public class MouseUtils {
    public static Point getMousePosition (JFrame frame) {
        Point mouse_pos = MouseInfo.getPointerInfo().getLocation();

        return new Point(mouse_pos.x - frame.getX() - 7, mouse_pos.y - frame.getY() - 31);
    }

    public static boolean checkCollision(Point mouse_pos, Box box) {
        return
                mouse_pos.x >= box.getX() && mouse_pos.x < box.getX() + box.getWidth() - 1 &&
                mouse_pos.y >= box.getY() && mouse_pos.y < box.getY() + box.getHeight() - 1;
    }
}
