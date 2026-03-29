package program.gui.graphic.text.texts;

import program.gui.alignment.Alignment;
import program.gui.utils.ColorUtils;

import java.awt.*;

public class SelectedTextBox extends TextBox {
    protected boolean selected;
    protected int change;

    public SelectedTextBox () {

    }

    public SelectedTextBox (float x, float y, float width, float height, float textSize, int change, boolean selected, Alignment alignment, Color color, String text, Font font, boolean changeTextWidthOnResize, boolean changeTextHeightOnResize) {
        super(x, y, width, height, textSize, alignment, color, text, font, changeTextWidthOnResize, changeTextHeightOnResize);

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
