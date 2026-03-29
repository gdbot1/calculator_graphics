package program.gui.graphic;

import java.awt.*;

public abstract class AbstractGraphic implements IGraphic {
    protected float x, y;
    protected Color color;

    public AbstractGraphic() {

    }

    public AbstractGraphic(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public float[] getPosition(){
        return new float[]{x, y};
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
}
