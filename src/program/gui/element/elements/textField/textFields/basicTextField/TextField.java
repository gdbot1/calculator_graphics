package program.gui.element.elements.textField.textFields.basicTextField;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.textField.textFields.AbstractBasicTextField;
import program.gui.graphic.rectangle.rectangles.SelectedBorder;
import program.gui.graphic.rectangle.rectangles.SelectedRectangle;
import program.gui.alignment.Alignment;
import program.gui.graphic.text.texts.TextBox;
import program.gui.utils.GraphicsUtils;
import program.time.Timer;

import javax.swing.*;
import java.awt.*;

public class TextField extends AbstractBasicTextField {
    protected boolean displayCursor;

    protected Timer timer;
    protected boolean cursorVisiblePeriod = false;

    public TextField() {
        displayCursor = false;
        timer = new Timer(Timer.getDelay(1));
    }

    public TextField(float x, float y, float width, float height, String text) {
        SelectedRectangle background = new SelectedRectangle(x, y, width, height, 0, 0, 20, new Color(150, 155, 160));
        SelectedRectangle cursorField = new SelectedRectangle(x, y, width, height, 0, 0, -255, new Color(0, 255, 255, 90));
        TextBox textBox = new TextBox(x, y, width, height, 0.5f, Alignment.LEFT, Color.black, text, new Font("", Font.PLAIN, 20), false, true);
        SelectedBorder border = new SelectedBorder(x, y, width, height, 0, 0, 0.05f, 20, new Color(100, 105, 110));

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.cursorFrom = this.cursorTo = text.length();
        this.textBox = textBox;
        this.background = background;
        this.cursorField = cursorField;
        this.border = border;
        this.scrollX = 0;
        this.textFieldSize = 0.9f;
        this.timer = new Timer(Timer.getDelay(1));

        displayCursor = false;
    }

    public void setDisplayCursor(boolean displayCursor) {
        this.displayCursor = displayCursor;
    }

    public void setCursorVisiblePeriod(boolean cursorVisiblePeriod) {
        this.cursorVisiblePeriod = cursorVisiblePeriod;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public boolean isCursorVisiblePeriod() {
        return cursorVisiblePeriod;
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {
        super.update(frame, rangeBox);

        if (timer.checkDelay()) {
            cursorVisiblePeriod = !cursorVisiblePeriod;
        }
    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        Graphics2D g2D = (Graphics2D) g;

        GraphicsUtils.setClip(g2D, this, rangeBox);

        drawBackGround(g);

        drawBorder(g);

        drawTextBox(g, rangeBox);

        if ((cursorVisiblePeriod || !cursorIsTogether()) && (displayCursor || isFocused())) {
            drawCursor(g, rangeBox);
        }

        GraphicsUtils.disableClip(g2D);
    }
}