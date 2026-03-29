package program.gui;

import program.gui.element.IElement;
import program.gui.element.elements.collection.ICollection;
import program.gui.element.elements.collection.panel.IPanel;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.listener.ElementKeyListener;
import program.gui.element.listener.ElementMouseListener;
import program.gui.element.listener.ElementMouseWheelListener;
import program.gui.utils.CollectionUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUI implements MouseListener, KeyListener, MouseWheelListener, ICollection<IElement> {
    protected CopyOnWriteArrayList<IElement> elements = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<ElementMouseListener> mouse_listeners = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<ElementKeyListener> key_listeners = new CopyOnWriteArrayList<>();
    protected CopyOnWriteArrayList<ElementMouseWheelListener> mouse_wheel_listeners = new CopyOnWriteArrayList<>();

    public GUI () {
    }

    public void update (JFrame frame, Box rangeBox) {
        for (IElement element : elements) {
            if (element.isEnabled()) element.update(frame, rangeBox);
        }
    }

    public void draw (Graphics g, Box rangeBox) {
        for (IElement element : elements) {
            if (element.isVisible()) {
                element.draw(g, rangeBox);
            }
        }
    }

    public void add(IElement element) {
        elements.add(element);
    }

    @Override
    public void add(int i, IElement element) {
        elements.add(i, element);
    }

    @Override
    public void set(int i, IElement element) {
        IElement indexElement = elements.get(i);

        if (indexElement instanceof ElementMouseListener) {
            mouse_listeners.remove(indexElement);
        }
        if (element instanceof ElementMouseListener) {
            mouse_listeners.add((ElementMouseListener) element);
        }

        if (indexElement instanceof ElementKeyListener) {
            key_listeners.remove(indexElement);
        }
        if (element instanceof ElementKeyListener) {
            key_listeners.add((ElementKeyListener) element);
        }

        elements.set(i, element);
    }

    @Override
    public void remove(IElement element) {
        elements.remove(element);

        if (element instanceof ElementMouseListener) {
            mouse_listeners.remove(element);
        }

        if (element instanceof ElementKeyListener) {
            key_listeners.remove(element);
        }
    }

    @Override
    public void remove(int i) {
        IElement element = elements.get(i);

        elements.remove(i);

        if (element instanceof ElementMouseListener) {
            mouse_listeners.remove(element);
        }

        if (element instanceof ElementKeyListener) {
            key_listeners.remove(element);
        }
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

    public void addMouseListener(ElementMouseListener listener) {
        mouse_listeners.add(listener);
    }

    public void addKeyListener(ElementKeyListener listener) {
        key_listeners.add(listener);
    }

    public void addMouseWheelListener(ElementMouseWheelListener listener) {
        mouse_wheel_listeners.add(listener);
    }

    public void addPanelListener(IPanel panel) {
        key_listeners.add(panel);
        mouse_listeners.add(panel);
        mouse_wheel_listeners.add(panel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed (MouseEvent e) {
        for (IElement element : elements) {
            element.disableFocus();
        }
        for (int i = mouse_listeners.size() - 1; i >= 0; i--) {
            if (mouse_listeners.get(i).mousePressed(e)) {
                break;
            }
        }
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        for (ElementMouseListener listener : mouse_listeners) {
            listener.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = key_listeners.size() - 1; i >= 0; i--) {
            if (key_listeners.get(i).keyPressed(e)) {
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (ElementKeyListener listener : key_listeners) {
            listener.keyReleased(e);
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        for (ElementMouseWheelListener listener : mouse_wheel_listeners) {
            if (listener.mouseWheel(e)) {
                break;
            }
        }
    }
}
