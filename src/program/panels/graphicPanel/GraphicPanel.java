package program.panels.graphicPanel;

import program.gui.element.IElement;
import program.gui.element.elements.collection.ICollection;
import program.gui.element.elements.collection.panel.AbstractPanel;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.BoxLayout;
import program.gui.element.elements.shape.shapes.RectangleShape;
import program.gui.graphic.rectangle.rectangles.Grid;
import program.gui.utils.GraphicsUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GraphicPanel extends AbstractPanel {
    protected float step;
    protected Grid grid;
    protected ICollection<IElement> equationsList;

    public GraphicPanel(float x, float y, float width, float height, float step, ICollection<IElement> equationsList, Grid grid) {
        super(x, y, width, height, new BoxLayout(new Box[]{
                new Box(0, 0, 1, 1)
        }));

        this.step = step;
        this.equationsList = equationsList;
        this.grid = grid;

        RectangleShape gridShape = new RectangleShape(grid);

        add(gridShape);
    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        super.draw(g, rangeBox);

        Graphics2D g2D = (Graphics2D) g;

        Box newRangeBox = Box.getIntersection(getBox(), rangeBox);

        Shape clipShape = g2D.getClip();

        GraphicsUtils.setClip(g2D, newRangeBox);

        grid.drawEquations(g, equationsList, step);

        GraphicsUtils.setClip(g2D, clipShape);
    }

    protected Point startMousePosition;
    protected boolean mousePressed = false;

    @Override
    public void onClick(MouseEvent e) {
        float scale = grid.getScale();

        startMousePosition = new Point((int) (mouse_position.x - grid.getBiasX() * scale), (int) (mouse_position.y - grid.getBiasY() * scale));
        mousePressed = true;

        super.onClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;

        super.mouseReleased(e);
    }

    @Override
    public boolean mouseWheel(MouseWheelEvent e) {
        if (mouseInside) {
            float beginScale = grid.getScale();

            float scale = Math.clamp(beginScale - (float)e.getWheelRotation() * beginScale / 10, 0.1f, 20);

            grid.setScale(scale);

            return true;
        }

        return false;
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {
        super.update(frame, rangeBox);

        if (mousePressed) {
            float scale = grid.getScale();

            float biasX = (mouse_position.x - startMousePosition.x) / scale;
            float biasY = (mouse_position.y - startMousePosition.y) / scale;

            grid.setBiasX(biasX);
            grid.setBiasY(biasY);
        }
    }
}
