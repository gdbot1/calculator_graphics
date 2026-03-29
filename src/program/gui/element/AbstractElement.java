package program.gui.element;

import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;

public abstract class AbstractElement implements IElement {
    protected float x, y, width, height, biasX, biasY;
    protected boolean visible = true, enabled = true, focused = false;

    public AbstractElement(float x, float y, float width, float height, float biasX, float biasY, float biasWidth, float biasHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.biasX = biasX;
        this.biasY = biasY;
    }

    public AbstractElement(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public AbstractElement() {

    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.focused = false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void disableFocus() {
        focused = false;
    }

    @Override
    public boolean isFocused() {
        return focused;
    }

    @Override
    public float[] getPosition() {
        return new float[]{x, y};
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float[] getBiasPosition() {
        return new float[]{biasX, biasY};
    }

    @Override
    public void setBiasPosition(float biasX, float biasY) {
        this.biasX = biasX;
        this.biasY = biasY;
    }

    @Override
    public float[] getGlobalPosition() {
        return new float[]{x + biasX, y + biasY};
    }

    @Override
    public float[] getSize() {
        return new float[]{width, height};
    }

    @Override
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Box getBox() {
        float[] global_position = getGlobalPosition();

        return new Box(global_position[0], global_position[1], width, height);
    }
}
