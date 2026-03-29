package program.gui.element.elements.collection.panel;

import program.gui.element.IElement;
import program.gui.element.clickable.AbstractClickableElement;
import program.gui.element.elements.collection.panel.layout.ILayout;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.listener.ElementKeyListener;
import program.gui.element.listener.ElementMouseListener;
import program.gui.element.listener.ElementMouseWheelListener;
import program.gui.utils.CollectionUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractPanel extends AbstractClickableElement implements IPanel {
    protected CopyOnWriteArrayList<IElement> elements = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<ElementMouseListener> mouse_listeners = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<ElementKeyListener> key_listeners = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<ElementMouseWheelListener> mouse_wheel_listeners = new CopyOnWriteArrayList<>();
    protected ILayout layout;

    public AbstractPanel() {

    }

    public AbstractPanel(float x, float y, float width, float height, ILayout layout) {
        super(x, y, width, height);

        this.layout = layout;
    }

    public void setLayout(ILayout layout) {
        this.layout = layout;
    }

    public ILayout getLayout() {
        return layout;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);

        for (IElement element : elements) {
            element.setBiasPosition(x + biasX, y + biasY);
        }
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);

        layoutElements();
    }

    @Override
    public void disableFocus() {
        for (IElement element : elements) {
            element.disableFocus();
        }
    }

    public void addMouseListener(ElementMouseListener listener) {
        if (listener != this) {
            mouse_listeners.add(listener);
        }
    }

    public void addKeyListener(ElementKeyListener listener) {
        if (listener != this) {
            key_listeners.add(listener);
        }
    }

    public void addMouseWheelListener(ElementMouseWheelListener listener) {
        if (listener != this) {
            mouse_wheel_listeners.add(listener);
        }
    }

    public void addPanelListener(IPanel panel) {
        if (panel != this) {
            key_listeners.add(panel);
            mouse_listeners.add(panel);
            mouse_wheel_listeners.add(panel);
        }
    }

    @Override
    public void add(IElement element) {
        elements.add(element);

        element.setBiasPosition(x + biasX, y + biasY);

        layoutElements();
    }

    @Override
    public void add(int i, IElement element) {
        elements.add(i, element);

        element.setBiasPosition(x + biasX, y + biasY);

        layoutElements();
    }

    @Override
    public void set(int i, IElement element) {
        IElement indexElement = elements.get(i);

        if (indexElement instanceof ElementMouseListener) {
            mouse_listeners.remove((ElementMouseListener) indexElement);
        }

        if (element instanceof ElementMouseListener) {
            mouse_listeners.add((ElementMouseListener) element);
        }

        elements.set(i, element);

        element.setBiasPosition(x + biasX, y + biasY);

        layoutElements();
    }

    @Override
    public void remove(IElement element) {
        element.disableFocus();

        elements.remove(element);

        if (element instanceof ElementMouseListener) {
            mouse_listeners.remove(element);
        }

        layoutElements();
    }

    @Override
    public void remove(int i) {
        elements.get(i).disableFocus();

        IElement element = elements.get(i);

        if (element instanceof ElementMouseListener) {
            mouse_listeners.remove(element);
        }

        elements.remove(i);

        layoutElements();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public IElement get(int i) {
        return elements.get(i);
    }

    @Override
    public int indexOf(IElement element) {
        return CollectionUtils.indexOf(elements, element);
    }

    @Override
    public void layoutElements() {
        layout.layoutElements(this);
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public boolean mousePressed(MouseEvent e) {
        super.mousePressed(e);

        if (!mouseInside) {
            return false;
        }

        for (ElementMouseListener mouse_listener : mouse_listeners) {
            if (mouse_listener.mousePressed(e)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (ElementMouseListener listener : mouse_listeners) {
            listener.mouseReleased(e);
        }

        super.mouseReleased(e);
    }

    @Override
    public boolean keyPressed(KeyEvent e) {
        for (ElementKeyListener listener : key_listeners) {
            if (listener.keyPressed(e)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (ElementKeyListener listener : key_listeners) {
            listener.keyReleased(e);
        }
    }

    @Override
    public boolean mouseWheel(MouseWheelEvent e) {
        for (ElementMouseWheelListener listener : mouse_wheel_listeners) {
            if (listener.mouseWheel(e)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {
        super.update(frame, rangeBox);

        Box newRangeBox = Box.getIntersection(getBox(), rangeBox);

        for (IElement element : elements) {
            element.update(frame, newRangeBox);
        }
    }

    @Override
    public void draw(Graphics g, Box rangeBox) {
        Box newRangeBox = Box.getIntersection(getBox(), rangeBox);

        for (IElement element : elements) {
            element.draw(g, newRangeBox);
        }
    }
}
