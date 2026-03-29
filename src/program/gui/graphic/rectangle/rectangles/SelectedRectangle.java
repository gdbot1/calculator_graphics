package program.gui.graphic.rectangle.rectangles;

import program.gui.utils.ColorUtils;

import java.awt.*;

public class SelectedRectangle extends BasicRectangle {
    protected boolean selected;
    protected int change;

    public SelectedRectangle() {

    }

    public SelectedRectangle(float x, float y, float width, float height, float arcWidth, float arcHeight, int change, Color color) {
        super(x, y, width, height, arcWidth, arcHeight, color);

        this.selected = false;
        this.change = change;
    }

    public SelectedRectangle(float x, float y, float width, float height, float arcWidth, float arcHeight, boolean selected, int change, Color color) {
        super(x, y, width, height, arcWidth, arcHeight, color);

        this.selected = selected;
        this.change = change;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public int getChange() {
        return change;
    }

    @Override
    public void setColor (Graphics g) {
        Color color = this.color;

        if (selected) {
            color = ColorUtils.lightColorRGB(color, change);
        }

        g.setColor(color);
    }
}
