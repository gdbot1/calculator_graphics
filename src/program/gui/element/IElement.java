package program.gui.element;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;

import javax.swing.*;
import java.awt.*;

public interface IElement {
    void update(JFrame frame, Box rangeBox);

    void draw(Graphics g, Box rangeBox);

    float[] getPosition();
    void setPosition(float x, float y);

    float[] getBiasPosition();
    void setBiasPosition(float biasX, float biasY);

    float[] getGlobalPosition();

    float[] getSize();
    void setSize(float width, float height);

    void setVisible(boolean visible);
    boolean isVisible();

    void setEnabled(boolean enabled);
    boolean isEnabled();

    void disableFocus();
    boolean isFocused();

    Box getBox();
}
