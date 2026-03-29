package program.panels.mainPanel;

import program.exception.ExceptionManager;
import program.gui.alignment.Alignment;
import program.gui.element.elements.collection.panel.AbstractPanel;
import program.gui.element.elements.collection.panel.layout.layouts.ItemLayout;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.BoxLayout;
import program.gui.element.elements.collection.panel.panels.Panel;
import program.gui.graphic.rectangle.rectangles.Grid;
import program.panels.equation.EquationListPanel;
import program.panels.graphicPanel.GraphicPanel;
import program.panels.numpad.NumPad;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends AbstractPanel {
    protected ExceptionManager exceptionManager;//Панель для вивода ошибкок.

    protected NumPad numPad;
    protected EquationListPanel list;
    protected GraphicPanel graphicsPanel;
    protected Panel itemPanel, padPanel, boxPanel;

    public MainPanel() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.layout = new BoxLayout(new Box[]{
                new Box(0, 0, 1, 0.66f),
                new Box(0, 0.66f, 1, 0.34f)
        });

        exceptionManager = new ExceptionManager();

        numPad = new NumPad(0, 0, 0, 0);
        list = new EquationListPanel(0, 0, 0, 0);

        list.addExceptionListener(exceptionManager);

        graphicsPanel = new GraphicPanel(0, 0, 0, 0, 1, list.getPanel(), new Grid(0, 0, 0, 0, 10, 0, 0, 1, Color.lightGray));

        numPad.addTextListener(list);

        float listDivPad = 1;

        padPanel = new Panel(0, 0, 0, 0, new Color(60, 65, 70, 80), new BoxLayout(new Box[]{
                new Box(0, 0, 1 / (listDivPad + 1), 1),
                new Box(1 / (listDivPad + 1), 0, 1 - 1 / (listDivPad + 1), 1)
        }));

        padPanel.add(numPad);
        padPanel.add(list);

        padPanel.addPanelListener(numPad);
        padPanel.addPanelListener(list);

        int columns = numPad.getColumns(), rows = numPad.getRows();

        itemPanel = new Panel(0, 0, 0, 0, new ItemLayout(columns + columns * listDivPad, rows + 1, Alignment.TOP));

        itemPanel.add(padPanel);

        itemPanel.addPanelListener(padPanel);

        boxPanel = new Panel(0, 0, 0, 0, Color.lightGray, new BoxLayout(new Box[]{
                new Box(0, 0, 1, 1)
        }));

        boxPanel.add(itemPanel);
        boxPanel.addPanelListener(itemPanel);

        add(graphicsPanel);
        add(boxPanel);

        addPanelListener(graphicsPanel);
        addPanelListener(boxPanel);
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {
        super.update(frame, rangeBox);

        exceptionManager.update();
    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        super.draw(g, rangeBox);

        exceptionManager.draw(g, rangeBox);
    }
}
