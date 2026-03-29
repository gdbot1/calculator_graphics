package program.gui.graphic.rectangle.rectangles;

import program.equations.Equation;
import program.equations.value.IValue;
import program.gui.element.IElement;
import program.gui.element.elements.collection.ICollection;
import program.gui.graphic.rectangle.AbstractRectangle;
import program.panels.equation.EquationPanel;

import java.awt.*;

public class Grid extends AbstractRectangle {
    protected float step, biasX, biasY, scale;

    public Grid(float x, float y, float width, float height, float step, float biasX, float biasY, float scale, Color color) {
        super(x, y, width, height, 0, 0, color);

        this.step = step;
        this.biasX = biasX;
        this.biasY = biasY;
        this.scale = scale;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public float getBiasX() {
        return biasX;
    }

    public void setBiasX(float biasX) {
        this.biasX = biasX;
    }

    public float getBiasY() {
        return biasY;
    }

    public void setBiasY(float biasY) {
        this.biasY = biasY;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        int cellsInWidth = (int) (((width / 2 / step) + 1) / scale), cellsInHeight = (int) (((height / 2 / step) + 1) / scale);

        int toX = cellsInWidth - (int)(biasX / step) + 1, fromX = -cellsInWidth - (int)(biasX / step);

        for (int i = fromX; i < toX; i++) {
            float x = width / 2 + (i * step + biasX) * scale;

            if (x < this.x || x > this.x + width) {
                continue;
            }

            if (i == 0) {
                g.setColor(color.darker());
            }
            else{
                g.setColor(color);
            }

            g.drawLine((int) x, (int)y, (int)x, (int)(y + height));
        }

        int toY = cellsInHeight - (int)(biasY / step) + 1, fromY = -cellsInHeight - (int)(biasY / step);

        for (int i = fromY; i < toY; i++) {
            float y = height / 2 + (i * step + biasY) * scale;

            if (y < this.y || y > this.y + height) {
                continue;
            }

            if (i == 0) {
                g.setColor(color.darker());
            }
            else{
                g.setColor(color);
            }

            g.drawLine((int) x, (int)y, (int)(x + width), (int)y);
        }
    }

    public void drawEquation(Graphics g, Equation equation, float graphicStep) {
        IValue value = equation.getEquation();

        if (value == null) {
            return;
        }

        g.setColor(equation.getColor());

        float from = (- width / 2) / scale - biasX * 2, to = (width / 2) / scale - biasX * 2;

        float[] beginPoint = null;

        for (float i = from; i < to; i+= graphicStep / scale) {
            float x = i + biasX;

            float y = -value.getValue(x);

            if (Float.isInfinite(y) || Float.isNaN(y)) {
                beginPoint = null;
                continue;
            }

            float pointX = (x * scale + width / 2 + biasX * scale), pointY = (y * scale + height / 2 + biasY * scale);

            if (beginPoint != null) {
                g.drawLine((int)pointX, (int)pointY, (int)beginPoint[0], (int)beginPoint[1]);
            }

            beginPoint = new float[]{pointX, pointY};
        }
    }

    public <T extends IElement> void drawEquations(Graphics g, ICollection<T> collection, float graphicStep) {
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i) instanceof EquationPanel equationPanel) {
                Equation equation = equationPanel.getEquation();

                if (equation == null) {
                    continue;
                }

                drawEquation(g, equation, graphicStep);
            }
        }
    }

    @Override
    public void fill(Graphics g) {
        draw(g);
    }
}
