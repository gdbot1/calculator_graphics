package program.gui.graphic.text.texts;

import program.gui.utils.ColorUtils;

import java.awt.*;

public class SelectedText extends BasicText {
    protected boolean selected;
    protected int change;

    public SelectedText() {

    }

    public SelectedText(float x, float y, int change, Color color, String text, Font font) {
        super(x, y, color, text, font);

        this.selected = false;
        this.change = change;
    }

    public SelectedText(float x, float y, boolean selected, int change, Color color, String text, Font font) {
        super(x, y, color, text, font);

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
    public void setColor(Graphics g) {
        Color color = this.color;

        if (selected) {
            color = ColorUtils.lightColorRGB(color, change);
        }

        g.setColor(color);
    }
}
