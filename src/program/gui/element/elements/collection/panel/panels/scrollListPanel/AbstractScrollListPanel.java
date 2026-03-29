package program.gui.element.elements.collection.panel.panels.scrollListPanel;

import program.gui.element.elements.collection.panel.AbstractPanel;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.BoxLayout;
import program.gui.element.elements.collection.panel.panels.ListPanel;
import program.gui.element.elements.scrollBar.IScrollBar;

public abstract class AbstractScrollListPanel extends AbstractPanel {
    protected IScrollBar scrollBar;
    protected ListPanel listPanel;

    public AbstractScrollListPanel(float x, float y, float width, float height, float scrollBarWidth, IScrollBar scrollBar, ListPanel listPanel) {
        this.scrollBar = scrollBar;
        this.listPanel = listPanel;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.layout = new BoxLayout(new Box[]{
                new Box(0, 0, 1 - scrollBarWidth, 1),
                new Box(1 - scrollBarWidth, 0, scrollBarWidth, 1)
        });

        listPanel.setScrollBar(scrollBar);

        add(listPanel);
        add(scrollBar);

        addMouseListener(listPanel);
        addMouseListener(scrollBar);

        addMouseWheelListener(listPanel);
    }

    public IScrollBar getScrollBar() {
        return scrollBar;
    }

    public ListPanel getPanel() {
        return listPanel;
    }

    public void setScrollBar(IScrollBar scrollBar) {
        this.scrollBar = scrollBar;

        if (size() > 1) {
            set(1, scrollBar);
        }
        else if (size() == 1) {
            add(scrollBar);
        }
        else {
            throw new RuntimeException("You can't change scrollBar, when mainPanel (this) hasn't listPanel.");
        }
    }

    public ListPanel getListPanel() {
        return listPanel;
    }

    public void setListPanel(ListPanel listPanel) {
        this.listPanel = listPanel;

        if (size() > 0) {
            set(0, listPanel);
        } else {
            add(listPanel);
        }
    }
}