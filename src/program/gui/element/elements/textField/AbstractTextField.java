package program.gui.element.elements.textField;

import program.gui.element.clickable.AbstractClickableElement;
import program.gui.utils.FontUtils;
import program.gui.utils.InputUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class AbstractTextField extends AbstractClickableElement implements ITextField {
    protected boolean ctrl = false, shift = false;

    protected String text;
    protected int cursorFrom = 0, cursorTo = 0;
    protected boolean canInputAnywhere = false;

    public AbstractTextField() {
        text = "";
    }

    public AbstractTextField(float x, float y, float width, float height, String text, int cursorFrom, int cursorTo) {
        super(x, y, width, height);

        this.text = text;

        setCursor(cursorFrom, cursorTo);
    }

    public float getCursorX(String text, Font font, int cursorFrom) {
        return FontUtils.getTextWidth(text.substring(0, cursorFrom), font);
    }

    public float getCursorWidth(String text, Font font, int cursorFrom, int cursorTo) {
        return getCursorX(text, font, Math.max(cursorFrom, cursorTo)) - getCursorX(text, font, Math.min(cursorFrom, cursorTo));
    }

    public void setInputAnywhere(boolean canInputAnywhere) {
        this.canInputAnywhere = canInputAnywhere;
    }

    public boolean isInputAnywhere() {
        return canInputAnywhere;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;

        setCursor(text.length(), text.length());
    }

    @Override
    public int[] getCursor() {
        return new int[]{cursorFrom, cursorTo};
    }

    @Override
    public void setCursor(int from, int to) {
        this.cursorFrom = Math.clamp(from, 0, text.length());
        this.cursorTo = Math.clamp(to, 0, text.length());
    }

    public boolean cursorIsTogether() {
        return cursorFrom == cursorTo;
    }

    @Override
    public void onClick(MouseEvent e) {
        focused = true;
    }

    @Override
    public boolean keyPressed(KeyEvent e) {
        int key_code = e.getKeyCode();

        if (focused || isInputAnywhere()) {
            switch (key_code) {
                case KeyEvent.VK_CONTROL -> ctrl = true;
                case KeyEvent.VK_SHIFT -> shift = true;

                case KeyEvent.VK_RIGHT -> InputUtils.moveInRight(this, ctrl, shift);
                case KeyEvent.VK_LEFT -> InputUtils.moveInLeft(this, ctrl, shift);

                case KeyEvent.VK_BACK_SPACE -> InputUtils.backspace(this, ctrl, shift);

                default -> InputUtils.enterChar(this, e, ctrl, shift);
            }

            return true;
        }

        return false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key_code = e.getKeyCode();

        switch (key_code) {
            case KeyEvent.VK_CONTROL -> ctrl = false;
            case KeyEvent.VK_SHIFT -> shift = false;
        }
    }
}