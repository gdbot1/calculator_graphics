package program.gui.element.elements.collection.panel.layout.layouts.boxLayout;

public class Box {
    protected float x ,y, width, height;

    public Box(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public static Box getIntersection(Box box1, Box box2) {
        if (box1 == null) {
            return box2;
        }
        else if (box2 == null) {
            return box1;
        }

        float x1 = Math.max(box1.getX(), box2.getX()), x2 = Math.min(box1.getX() + box1.getWidth(), box2.getX() + box2.getWidth());
        float y1 = Math.max(box1.getY(), box2.getY()), y2 = Math.min(box1.getY() + box1.getHeight(), box2.getY() + box2.getHeight());

        float width = x2 - x1, height = y2 - y1;

        return new Box(x1, y1, width, height);
    }

    @Override
    public String toString() {
        return "Box[x:"+x+", y:"+y+", w:"+width+", h:"+height+"]";
    }
}
