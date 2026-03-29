package program.panels.numpad;

import program.gui.element.elements.button.IButton;
import program.gui.element.elements.button.buttons.textButtons.TextButton;
import program.gui.element.elements.collection.panel.AbstractPanel;
import program.gui.element.elements.collection.panel.layout.layouts.GridLayout;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.BoxLayout;
import program.gui.element.elements.collection.panel.panels.Panel;
import program.gui.element.elements.textField.textFields.basicTextField.TextField;
import program.gui.element.listener.ButtonListener;
import program.gui.utils.InputUtils;
import program.panels.numpad.event.AddTextListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class NumPad extends AbstractPanel implements ButtonListener, AddTextListener {
    protected List<AddTextListener> addTextListeners = new ArrayList<>();
    protected Panel buttonPanel;

    protected List<TextButton> buttons;
    protected TextField field;

    protected int rows = 4, columns = 5;

    public NumPad(float x, float y, float width, float height) {
        buttonPanel = new Panel(0, 0, 0, 0, new GridLayout(columns, rows));

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.layout = new BoxLayout(new Box[]{
                new Box(0, 0, 1, (float)1 / (rows + 1)),
                new Box(0, (float)1 / (rows + 1), 1, (float)rows / (rows + 1))
        });

        this.field = new TextField(0, 0, 0, 0, "");
        this.field.getTextBox().setTextSize(0.4f);

        add(field);
        addMouseListener(field);
        addKeyListener(field);

        field.setDisplayCursor(true);
        field.setInputAnywhere(true);

        add(buttonPanel);
        addMouseListener(buttonPanel);

        buttons = new ArrayList<>();

        initButtons();

        add(buttonPanel);
    }

    public void initButtons() {
        Color darkColor = new Color(170, 175, 180);

        addButton("1", null).addButton("2", null).addButton("3", null).addButton("+", darkColor).addButton("<", darkColor);
        addButton("4", null).addButton("5", null).addButton("6", null).addButton("-", darkColor).addButton("X", darkColor);
        addButton("7", null).addButton("8", null).addButton("9", null).addButton("*", darkColor).addButton("()", darkColor);
        addButton("_", darkColor).addButton("0", null).addButton(".", darkColor).addButton("/", darkColor).addButton("add", darkColor);
    }

    public TextButton getButton(int x, int y) {
        int id = y * columns + x;

        if (id < 0 || id >= buttons.size()) {
            return null;
        }

        return buttons.get(id);
    }

    public NumPad addButton(String title, Color color) {
        TextButton button = new TextButton(0, 0, 0, 0, title);

        if (color != null) {
            button.getBackground().setColor(color);
        }

        button.getTextBox().setTextSize(0.4f);

        buttonPanel.add(button);
        buttons.add(button);

        buttonPanel.addMouseListener(button);

        button.setListener(this);

        return this;
    }

    @Override
    public void onButtonPressed(IButton button) {
        if (button instanceof TextButton textButton) {
            String text = textButton.getText();

            switch (text) {
                case "()" -> {
                    InputUtils.enterString(field, textButton.getText());
                    field.setCursor(field.getCursor()[0] - 1, field.getCursor()[0] - 1);
                }
                case "<" -> InputUtils.backspace(field, false, false);
                case "_" -> InputUtils.enterString(field, " ");
                case "add" -> addText(field.getText());
                default -> InputUtils.enterString(field, text);
            }
        }
    }

    @Override
    public void addText(String text) {
        for (AddTextListener listener : addTextListeners) {
            listener.addText(text);
        }
    }

    public void addTextListener(AddTextListener listener) {
        if (this != listener) {
            addTextListeners.add(listener);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
