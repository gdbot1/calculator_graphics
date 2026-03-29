package program.gui.element.elements.collection.panel.panels;

import program.gui.element.elements.collection.panel.AbstractPanel;
import program.gui.element.elements.collection.panel.layout.layouts.ListLayout;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.scrollBar.IScrollBar;
import program.gui.element.listener.ElementMouseWheelListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListPanel extends AbstractPanel implements ElementMouseWheelListener {
    protected IScrollBar scrollBar;
    protected ListLayout listLayout;

    public ListPanel(float x, float y, float width, float height, float boxHeight, IScrollBar scrollBar) {
        mouse_listeners = new CopyOnWriteArrayList<>();
        elements = new CopyOnWriteArrayList<>();

        listLayout = new ListLayout(0, boxHeight);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layout = listLayout;
        this.scrollBar = scrollBar;
    }

    public ListPanel(float x, float y, float width, float height, ListLayout listLayout) {
        mouse_listeners = new CopyOnWriteArrayList<>();
        elements = new CopyOnWriteArrayList<>();

        this.listLayout = listLayout;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layout = listLayout;
    }

    public ListPanel(float x, float y, float width, float height, ListLayout listLayout, IScrollBar scrollBar) {
        mouse_listeners = new CopyOnWriteArrayList<>();
        elements = new CopyOnWriteArrayList<>();

        this.listLayout = listLayout;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layout = listLayout;
        this.scrollBar = scrollBar;
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {
        if (scrollBar == null) {
            listLayout.setScroll(0);

            layoutElements();
        }
        else {
            if (scrollBar.isPressed()) {
                listLayout.setScroll(scrollBar.getScroll());

                layoutElements();
            }

            float listHeight = listLayout.getListHeight(this);

            scrollBar.setScrollButtonSize(Math.clamp(height / listHeight, 0, 1));
        }

        super.update(frame, rangeBox);
    }

    public IScrollBar getScrollBar() {
        return scrollBar;
    }

    public void setScrollBar(IScrollBar scrollBar) {
        this.scrollBar = scrollBar;
    }

    public void setListLayout(ListLayout listLayout) {
        this.listLayout = listLayout;
    }

    public ListLayout getListLayout() {
        return listLayout;
    }

    @Override
    public void draw(Graphics g, Box box) {
        float[] position = getGlobalPosition();

        g.setColor(new Color(255, 0, 0, 80));
        //g.fillRect((int)position[0], (int)position[1], (int)width, (int)height);

        super.draw(g, box);
    }

    @Override
    public boolean mouseWheel(MouseWheelEvent e) {
        if (mouseInside) {
            float scroll = Math.clamp(listLayout.getScroll() + (float)e.getWheelRotation() / size(), 0, 1);

            listLayout.setScroll(scroll);
            scrollBar.setScroll(scroll);

            return true;
        }

        return false;
    }
}
