package program.gui.utils;

import program.gui.element.IElement;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;

import java.awt.*;

public class GraphicsUtils {
    public static void setClip(Graphics2D g2D, Shape shape) {
        g2D.setClip(shape);
    }

    public static void setClip(Graphics2D g2D, Box rangeBox) {
        if (rangeBox == null) {
            return;
        }

        g2D.setClip((int) rangeBox.getX(), (int) rangeBox.getY(), (int) rangeBox.getWidth() + 1, (int) rangeBox.getHeight() + 1);
    }

    public static void setClip(Graphics2D g2D, IElement element, Box rangeBox) {
        Box newRangeBox = Box.getIntersection(element.getBox(), rangeBox);

        g2D.setClip((int) newRangeBox.getX(), (int) newRangeBox.getY(), (int) newRangeBox.getWidth() + 1, (int) newRangeBox.getHeight() + 1);
    }

    public static void disableClip(Graphics2D g2D) {
        g2D.setClip(null);
    }
}
