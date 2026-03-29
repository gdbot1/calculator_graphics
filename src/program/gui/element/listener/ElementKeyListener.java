package program.gui.element.listener;

import java.awt.event.KeyEvent;

public interface ElementKeyListener {
    boolean keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);
}
