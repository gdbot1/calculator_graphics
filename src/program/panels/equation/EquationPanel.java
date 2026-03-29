package program.panels.equation;

import program.equations.Equation;
import program.gui.alignment.Alignment;
import program.gui.element.elements.button.buttons.textButtons.TextButton;
import program.gui.element.elements.collection.panel.AbstractPanel;
import program.gui.element.elements.collection.panel.layout.layouts.ItemLayout;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.BoxLayout;
import program.gui.element.elements.collection.panel.panels.Panel;
import program.gui.element.elements.shape.shapes.RectangleShape;
import program.gui.element.elements.shape.shapes.TextBoxShape;
import program.gui.element.listener.ButtonListener;
import program.gui.graphic.rectangle.rectangles.BasicBorder;
import program.gui.graphic.rectangle.rectangles.BasicRectangle;
import program.gui.graphic.text.texts.TextBox;

import javax.swing.*;
import java.awt.*;

public class EquationPanel extends AbstractPanel {
    protected Panel buttonItemPanel, colorItemPanel;

    protected TextButton button;
    protected BasicRectangle background, textBoxBackground, colorField;
    protected BasicBorder backgroundBorder;
    protected TextBox textBox;
    protected Equation equation;

    public EquationPanel(float x, float y, float width, float height, Equation equation) {
        buttonItemPanel = new Panel(0, 0, 0, 0, new ItemLayout(1, 1, Alignment.CENTER));
        colorItemPanel = new Panel(0, 0, 0, 0, new ItemLayout(1, 1, Alignment.CENTER));

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.equation = equation;

        Box fieldBox = new Box(0, 0, 1, 1), textBoxBox = new Box(0.02f, 0.2f, 0.65f, 0.6f), colorBox = new Box(0.7f, 0.2f, 0.15f, 0.6f), buttonBox = new Box(0.85f, 0.2f, 0.15f, 0.6f);

        this.layout = new BoxLayout(new Box[]{
                fieldBox,//для фона
                fieldBox,//для обводки
                textBoxBox,//для фона
                textBoxBox,//для текста
                colorBox,//панель с цветом
                buttonBox//панель с кнопкой
        });

        Color borderColor = new Color(130, 135, 140), backgroundColor = new Color(150, 155, 160);

        background = new BasicRectangle(0, 0, 0, 0, 0, 0, backgroundColor);
        add(new RectangleShape(background));

        backgroundBorder = new BasicBorder(0, 0, 0, 0, 0, 0, 0.05f, borderColor);
        add(new RectangleShape(backgroundBorder));

        textBoxBackground = new BasicRectangle(0, 0, 0, 0, 0, 0, new Color(170, 175, 180));
        add(new RectangleShape(textBoxBackground));

        textBox = new TextBox(0, 0, 0, 0, 0.75f, Alignment.LEFT, Color.black, equation.getOrig(), new Font("", Font.PLAIN, 20), true, true);
        add(new TextBoxShape(textBox));

        colorField = new BasicRectangle(0, 0, 0, 0, 0, 0, equation.getColor());
        colorItemPanel.add(new RectangleShape(colorField));

        add(colorItemPanel);

        button = new TextButton(0, 0, 0, 0, "x");
        button.getTextBox().setTextSize(1);

        buttonItemPanel.add(button);
        buttonItemPanel.addMouseListener(button);

        add(buttonItemPanel);
        addMouseListener(buttonItemPanel);
    }

    @Override
    public void update(JFrame frame, Box rangeBox) {
        super.update(frame, rangeBox);

        textBox.setText(equation.getOrig());
    }

    public void setButtonListener(ButtonListener listener) {
        button.setListener(listener);
    }

    public Equation getEquation() {
        return this.equation;
    }

    public TextButton getButton() {
        return this.button;
    }
}
