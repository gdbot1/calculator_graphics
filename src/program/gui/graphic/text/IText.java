package program.gui.graphic.text;

import program.gui.graphic.IGraphic;

import java.awt.*;

public interface IText extends IGraphic {
    void setText(String text);
    String getText();

    void setFont(Font font);
    Font getFont();

    float[] getSizeBox();
}
