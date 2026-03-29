package program.gui.element.elements.scrollBar.scrollBars.basicScrollBars;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.scrollBar.scrollBars.AbstractBasicScrollBar;
import program.gui.graphic.rectangle.IRectangle;
import program.gui.graphic.rectangle.rectangles.BasicBorder;
import program.gui.graphic.rectangle.rectangles.BasicRectangle;
import program.gui.graphic.rectangle.rectangles.SelectedBorder;
import program.gui.graphic.rectangle.rectangles.SelectedRectangle;
import program.gui.utils.GraphicsUtils;

import java.awt.*;

public class ScrollBar extends AbstractBasicScrollBar {
    public ScrollBar() {

    }

    public ScrollBar(float x, float y, float width, float height, boolean vertical) {
        IRectangle background = new BasicRectangle(x, y, width, height, 0, 0, new Color(80, 85, 90));
        SelectedRectangle scrollButton = new SelectedRectangle(x, y, width, height, 0, 0, 20, new Color(130, 135, 140));
        SelectedBorder scrollButtonBorder = new SelectedBorder(x, y, width, height, 0, 0, 0.125f, 20, new Color(100, 105, 110));

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scrollButtonSize = 0.1f;
        this.vertical = vertical;
        this.background = background;
        this.backgroundBorder = null;
        this.scrollButton = scrollButton;
        this.scrollButtonBorder = scrollButtonBorder;
    }

    public ScrollBar(float x, float y, float width, float height, float scrollButtonSize, boolean vertical, IRectangle background, BasicBorder backgroundBorder, SelectedRectangle scrollButton, SelectedBorder scrollButtonBorder) {
        super(x, y, width, height, scrollButtonSize, vertical, background, backgroundBorder, scrollButton, scrollButtonBorder);
    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        Graphics2D g2D = (Graphics2D) g;

        GraphicsUtils.setClip(g2D, this, rangeBox);

        drawBackground(g);
        drawBackgroundBorder(g);

        if (isVertical()) {
            drawVerticalScrollButton(g);
            drawVerticalScrollButtonBorder(g);
        }
        else {
            drawHorizontalScrollButton(g);
            drawHorizontalScrollButtonBorder(g);
        }

        GraphicsUtils.disableClip(g2D);
    }
}
