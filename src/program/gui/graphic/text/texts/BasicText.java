package program.gui.graphic.text.texts;

import program.gui.graphic.text.AbstractText;

import java.awt.*;

public class BasicText extends AbstractText {
    public BasicText () {

    }

    public BasicText (float x, float y, Color color, String text, Font font) {
        super(x, y, color, text, font);
    }

    @Override
    public void draw(Graphics g) {
        setFont(g);
        setColor(g);
        drawText(g);


    }

    @Override
    public void fill(Graphics g) {
        draw(g);
    }
}
