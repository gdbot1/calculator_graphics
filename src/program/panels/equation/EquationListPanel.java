package program.panels.equation;

import program.equations.Equation;
import program.exception.ExceptionListener;
import program.gui.element.IElement;
import program.gui.element.elements.button.IButton;
import program.gui.element.elements.collection.panel.panels.ListPanel;
import program.gui.element.elements.collection.panel.panels.scrollListPanel.lists.ScrollListPanel;
import program.gui.element.listener.ButtonListener;
import program.panels.numpad.event.AddTextListener;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class EquationListPanel extends ScrollListPanel implements ButtonListener, AddTextListener, ExceptionListener {
    protected CopyOnWriteArrayList<ExceptionListener> exceptionListeners = new CopyOnWriteArrayList<>();

    public EquationListPanel(float x, float y, float width, float height) {
        super(x, y, width, height);

        this.listPanel.getListLayout().setBoxHeight(0.2f);
    }

    public void add(Equation equation) {
        EquationPanel panel = new EquationPanel(0, 0, 0, 0, equation);

        panel.setButtonListener(this);

        getPanel().add(panel);
        getPanel().addMouseListener(panel);
    }

    public void addExceptionListener(ExceptionListener listener) {
        if (this != listener) {
            exceptionListeners.add(listener);
        }
    }

    @Override
    public void onButtonPressed(IButton button) {
        ListPanel elementPanel = getPanel();

        for (int i = 0; i < elementPanel.size(); i++) {
            IElement element = elementPanel.get(i);

            if (element instanceof EquationPanel currentPanel) {
                IButton currentButton = currentPanel.getButton();

                if (currentButton == button) {
                    getPanel().remove(currentPanel);
                }
            }
        }
    }

    @Override
    public void addText(String text) {
        try {
            add(new Equation(text));
        } catch (IOException e) {
            onException(e);
        }
    }

    @Override
    public void onException(IOException e) {
        for (ExceptionListener listener : exceptionListeners) {
            listener.onException(e);
        }
    }
}
