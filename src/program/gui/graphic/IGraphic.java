package program.gui.graphic;

import java.awt.*;

public interface IGraphic {
    Color getColor();
    void setColor(Color color);

    float[] getPosition();
    void setPosition(float x, float y);

    void draw(Graphics g);

    void fill(Graphics g);
}
