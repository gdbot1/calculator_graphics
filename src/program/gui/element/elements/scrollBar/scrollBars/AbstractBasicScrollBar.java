package program.gui.element.elements.scrollBar.scrollBars;

import program.gui.element.elements.scrollBar.AbstractScrollBar;
import program.gui.graphic.rectangle.IRectangle;
import program.gui.graphic.rectangle.rectangles.BasicBorder;
import program.gui.graphic.rectangle.rectangles.SelectedBorder;
import program.gui.graphic.rectangle.rectangles.SelectedRectangle;
import program.gui.utils.MouseUtils;

import java.awt.*;

public abstract class AbstractBasicScrollBar extends AbstractScrollBar {
    protected IRectangle background;
    protected BasicBorder backgroundBorder;
    protected SelectedRectangle scrollButton;
    protected SelectedBorder scrollButtonBorder;

    public AbstractBasicScrollBar() {

    }

    public AbstractBasicScrollBar(float x, float y, float width, float height, float scrollButtonSize, boolean vertical, IRectangle background, BasicBorder backgroundBorder, SelectedRectangle scrollButton, SelectedBorder scrollButtonBorder) {
        super(x, y, width, height, scrollButtonSize, vertical);

        this.background = background;
        this.backgroundBorder = backgroundBorder;
        this.scrollButton = scrollButton;
        this.scrollButtonBorder = scrollButtonBorder;
    }

    public IRectangle getBackground() {
        return background;
    }

    public void setBackground(IRectangle background) {
        this.background = background;
    }

    public BasicBorder getBackgroundBorder() {
        return backgroundBorder;
    }

    public void setBackgroundBorder(BasicBorder backgroundBorder) {
        this.backgroundBorder = backgroundBorder;
    }

    public SelectedRectangle getScrollButton() {
        return scrollButton;
    }

    public void setScrollButton(SelectedRectangle scrollButton) {
        this.scrollButton = scrollButton;
    }

    public SelectedBorder getScrollButtonBorder() {
        return scrollButtonBorder;
    }

    public void setScrollButtonBorder(SelectedBorder scrollButtonBorder) {
        this.scrollButtonBorder = scrollButtonBorder;
    }

    public float getScrollX() {
        return getScrollFieldWidth() * scroll;
    }

    public float getScrollY() {
        return getScrollFieldHeight() * scroll;
    }

    protected boolean mouseInsideOfScrollButton() {
        return MouseUtils.checkCollision(mouse_position, scrollButton.getBox());
    }

    public void drawBackground(Graphics g) {
        if (background == null) {
            return;
        }

        float[] global_position = getGlobalPosition();

        background.setPosition(global_position[0], global_position[1]);
        background.setSize(width, height);

        background.fill(g);
    }

    public void drawBackgroundBorder(Graphics g) {
        if (backgroundBorder == null) {
            return;
        }

        float[] global_position = getGlobalPosition();

        backgroundBorder.setPosition(global_position[0], global_position[1]);
        backgroundBorder.setSize(width, height);

        backgroundBorder.draw(g);
    }

    public void drawVerticalScrollButton(Graphics g) {
        if (scrollButton == null) {
            return;
        }

        float[] global_position = getGlobalPosition();

        float y = getScrollY(), height = getScrollButtonHeightInPixels();

        scrollButton.setPosition(global_position[0], global_position[1] + y);
        scrollButton.setSize(width, height);

        scrollButton.setSelected(mousePressed);

        scrollButton.fill(g);
    }

    public void drawVerticalScrollButtonBorder(Graphics g) {
        if (scrollButtonBorder == null) {
            return;
        }

        float[] global_position = getGlobalPosition();

        float y = getScrollY(), height = getScrollButtonHeightInPixels();

        scrollButtonBorder.setPosition(global_position[0], global_position[1] + y);
        scrollButtonBorder.setSize(width, height);

        scrollButtonBorder.setSelected(mousePressed);

        scrollButtonBorder.draw(g);
    }

    public void drawHorizontalScrollButton(Graphics g) {
        if (scrollButton == null) {
            return;
        }

        float[] global_position = getGlobalPosition();

        float x = getScrollX(), width = getScrollButtonWidthInPixels();

        scrollButton.setPosition(global_position[0] + x, global_position[1]);
        scrollButton.setSize(width, height);

        scrollButton.setSelected(mousePressed);

        scrollButton.fill(g);
    }

    public void drawHorizontalScrollButtonBorder(Graphics g) {
        if (scrollButtonBorder == null) {
            return;
        }

        float[] global_position = getGlobalPosition();

        float x = getScrollX(), width = getScrollButtonWidthInPixels();

        scrollButtonBorder.setPosition(global_position[0] + x, global_position[1]);
        scrollButtonBorder.setSize(width, height);

        scrollButtonBorder.setSelected(mousePressed);

        scrollButtonBorder.draw(g);
    }
}
