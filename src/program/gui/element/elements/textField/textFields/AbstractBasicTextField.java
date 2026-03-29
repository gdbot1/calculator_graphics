package program.gui.element.elements.textField.textFields;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.textField.AbstractTextField;
import program.gui.graphic.rectangle.rectangles.SelectedBorder;
import program.gui.graphic.rectangle.rectangles.SelectedRectangle;
import program.gui.graphic.text.texts.TextBox;
import program.gui.utils.GraphicsUtils;
import program.time.Timer;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractBasicTextField extends AbstractTextField {
    protected float scrollX, textFieldSize;
    protected SelectedRectangle background, cursorField;
    protected SelectedBorder border;
    protected TextBox textBox;

    public AbstractBasicTextField() {
    }

    public AbstractBasicTextField(float x, float y, float width, float height, String text, int cursorFrom, int cursorTo, TextBox textBox, SelectedRectangle background, SelectedRectangle cursorField, SelectedBorder border, float scrollX, float textFieldSize) {
        super(x, y, width, height, text, cursorFrom, cursorTo);

        this.textBox = textBox;
        this.background = background;
        this.cursorField = cursorField;
        this.border = border;
        this.scrollX = scrollX;
        this.textFieldSize = textFieldSize;
    }

    public TextBox getTextBox() {
        return textBox;
    }

    public void setCursorField(SelectedRectangle cursorField) {
        this.cursorField = cursorField;
    }

    public SelectedRectangle getCursorField() {
        return cursorField;
    }

    public void setTextBox(TextBox textBox) {
        this.textBox = textBox;
    }

    public SelectedRectangle getBackground() {
        return background;
    }

    public void setBackground(SelectedRectangle background) {
        this.background = background;
    }

    public SelectedBorder getBorder() {
        return border;
    }

    public void setBorder(SelectedBorder border) {
        this.border = border;
    }

    public float getScrollX() {
        return scrollX;
    }

    public void setScrollX(float scrollX) {
        this.scrollX = scrollX;
    }

    public float getTextFieldSize() {
        return textFieldSize;
    }

    public void setTextFieldSize(float textFieldSize) {
        this.textFieldSize = textFieldSize;
    }

    protected float getTextFieldSizeInPixels() {
        return width * textFieldSize;
    }

    @Override
    public void update (JFrame frame, Box rangeBox) {
        super.update(frame, rangeBox);

        updateTextFieldScroll();
    }

    protected void updateTextFieldScroll() {
        float textFieldWidthInPixels = getTextFieldSizeInPixels();

        //Если курсор находиться за левой гранью.
        float minCursorX = getCursorX(text, textBox.getFont(), cursorTo);
        if (scrollX * height + minCursorX < 0) {
            scrollX = - minCursorX / height;
        }

        //Если курсор находится за правой гранью.
        float maxCursorX = getCursorX(text, textBox.getFont(), cursorTo);
        if ((maxCursorX - textFieldWidthInPixels) + (scrollX * height) > 0) {
            scrollX = (textFieldWidthInPixels - maxCursorX) / height;
        }

        float textWidth = getCursorX(text, textBox.getFont(), text.length());

        //Если текст слишком перекручен вправо.
        if (scrollX < 0 && textWidth < textFieldWidthInPixels) {
            scrollX = 0;
        }

        //Если текст слишком перекручен влево.
        float x = textWidth - (textFieldWidthInPixels - scrollX * height);

        if (textWidth > textFieldWidthInPixels && x < 0) {
            scrollX = -(textWidth - textFieldWidthInPixels) / height;
        }
    }

    public void drawBackGround(Graphics g) {
        float[] global_position = getGlobalPosition();

        background.setPosition(global_position[0], global_position[1]);
        background.setSize(width, height);

        background.setSelected(focused || isInputAnywhere());

        background.fill(g);
    }

    public void drawTextBox(Graphics g, Box rangeBox) {
        Graphics2D g2D = (Graphics2D) g;

        float[] global_position = getGlobalPosition();

        float biasX = (width - getTextFieldSizeInPixels()) / 2;

        textBox.setText(text);
        textBox.setPosition(global_position[0] + scrollX * height + biasX, global_position[1]);

        textBox.setSize(textBox.getSizeBox()[0], height);

        float rangeX = global_position[0] + biasX, rangeWidth = this.width - biasX * 2;

        Box newRangeBox = Box.getIntersection(new Box(rangeX, global_position[1], rangeWidth, height), rangeBox);

        Shape clipShape = g2D.getClip();

        GraphicsUtils.setClip(g2D, newRangeBox);

        textBox.draw(g);

        GraphicsUtils.setClip(g2D, clipShape);
    }

    public void drawCursor(Graphics g, Box rangeBox) {
        Graphics2D g2D = (Graphics2D) g;

        float[] global_position = getGlobalPosition();

        float biasX = (width - getTextFieldSizeInPixels()) / 2;

        boolean cursorTogether = cursorFrom == cursorTo;

        float cursorX = getCursorX(text, textBox.getFont(), Math.min(cursorFrom, cursorTo));
        float cursorWidth = getCursorWidth(text, textBox.getFont(), cursorFrom, cursorTo);
        float singleCursorWidth = cursorTogether ? height / 19 : 0;
        float textSize = textBox.getTextSize();

        float x = global_position[0] + cursorX + scrollX * height - singleCursorWidth / 2 + biasX, y = global_position[1] + height / 2 - height * textSize / 2;
        float width = cursorWidth + singleCursorWidth, height = this.height * textSize;

        cursorField.setPosition(x, y);
        cursorField.setSize(width, height);

        cursorField.setSelected(cursorTogether);

        float rangeX = global_position[0] + biasX - singleCursorWidth, rangeWidth = this.width - biasX * 2 + singleCursorWidth * 2;

        Box newRangeBox = Box.getIntersection(new Box(rangeX, y, rangeWidth, height), rangeBox);

        Shape clipShape = g2D.getClip();

        GraphicsUtils.setClip(g2D, newRangeBox);

        cursorField.fill(g);

        GraphicsUtils.setClip(g2D, clipShape);
    }

    public void drawBorder(Graphics g) {
        float[] global_position = getGlobalPosition();

        border.setPosition(global_position[0], global_position[1]);
        border.setSize(width, height);

        border.setSelected(focused);

        border.draw(g);
    }
}
