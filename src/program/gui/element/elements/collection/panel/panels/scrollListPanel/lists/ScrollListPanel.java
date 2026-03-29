package program.gui.element.elements.collection.panel.panels.scrollListPanel.lists;

import program.gui.element.elements.collection.panel.layout.layouts.ListLayout;
import program.gui.element.elements.collection.panel.panels.ListPanel;
import program.gui.element.elements.collection.panel.panels.scrollListPanel.AbstractScrollListPanel;
import program.gui.element.elements.scrollBar.scrollBars.basicScrollBars.ScrollBar;

public class ScrollListPanel extends AbstractScrollListPanel {
    public ScrollListPanel(float x, float y, float width, float height, float scrollFieldWidth, ScrollBar scrollBar, ListPanel panel) {
        super(x, y, width, height, scrollFieldWidth, scrollBar, panel);
    }

    public ScrollListPanel(float x, float y, float width, float height) {
        super(x, y, width, height, 0.05f, new ScrollBar(x, y, width, height, true), new ListPanel(x, y, width, height, new ListLayout(0, 0.1f)));
    }
}
