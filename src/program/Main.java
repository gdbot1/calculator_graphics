package program;

import program.equations.utils.StringBuilderUtils;
import program.gui.GUI;
import program.gui.swingElement.Frame;
import program.gui.utils.FrameUtils;
import program.panels.mainPanel.MainPanel;
import program.time.Timer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Цей проєкт був зробленний в учебних цілях.
 * Автор Валерій Молочко 11В класс (дата створення проєкта - 27.02.25).
 * Youtube-канал: @gdbot1
 * Мета: Дослідження та покращення знань в облості проєктування програм.
 * Мета программи: Парсінг стрічки, збереження рівняння у вигляді об'єкту классу, відображення графіку на єкрані.

 * Для того, щоб почати працюванти в программі треба:
 * 1) В текстовому полі циферблата ввести рівняння (X - 12 * X).
 * 2) Натиснути на кнопку add в тому-самому циферблаті.
 * 3) В листі справа з'явиться введена раніше функція.
 *    Ця функція також відобразиться на єкрані.
 *    Функція буде мати колір, як в листі.

 * Программа використовує парсінг у 4 етапи:
 * 1) Вилучення зайвих знаків (пробілів тощо).
 * 2) Рекурсивна обробка скобок. Враховування функцій (cos, pow, log, sqrt).
 * 3) Обробка операторів 2 разряду (*, /).
 * 4) Обробка операторів 1рразряду (+, -).

 * При обробці операторів парсер замінює блок на ключ.
 * Блок - це частина рівняння, яка групує елементи через оператор. Наприклад: 4+5, 25/3, X*12.
 * Оператор - це умовний знак, кий вказує на дію між числами в блоці. Наприклад: +, -, *, /.
 * Ключ - це умовна мітка в рівнянніб яка береже ссилку на минулу дію (<id>). Наприклад: <0>, 0 - це ссилка на нульову комірку в сховищі.
 * Сховище - це набор ключ-корінь, він береже залежність ключа та відгалуження рівняння.
 * Відгалуження рівняння - це послідовність дій в рівнянні.

 * Рівнянна парситься послідовно.
 * 5 + 4 * 2 - 8
 * 1 дія - це множення.
 * 4 * 2 -> mul(4, 2) - це початок відгалудження.
 * Тепер це відгалуждення зберігаєтья в сховище під ключом <0>.
 * Теперь рівняння втглядає так: 5 + <0> - 8
 * Наступна дія - це сумма: 5 + <0>.
 * 5 + <0> -> sum(5, <0>) -> sum(5, mul(4, 2)) - це відгалудження зберігається під індексом <1>.
 * Тепер рівняння виглядає так: <1> - 8
 * Наступна дія - різниця: <1> - 8
 * <1> - 8 -> sub(<1>, 8) -> sub(sum(5, mul(4, 2)), 8) -> <2>
 * Тепер рівняння має вигляд <2>, а під цим індексом повна послідовність дій рівняння.

 * Весь UI написан без сторонніх бібліотек (лише з AWT).
 * UI працює на властивості малювання awt.Graphics.
 **/
public class Main extends JPanel {
    public static void main(String[] args) {
        new Main();
    }

    //Main frame.
    Frame frame;

    //GUI panel.
    GUI gui;

    //Main program panel.
    MainPanel mainPanel;

    //Timer for program loop.
    Timer timer;

    public Main() {
        StringBuilderUtils.init();//Init parser.

        mainPanel = new MainPanel();//init program panel.

        initGui();//init gui panel.

        initFrame();//init main frame.

        timer = new Timer(Timer.getDelay(100));//init timer.

        loop();//start loop.
    }

    public void initGui() {
        gui = new GUI();

        gui.add(mainPanel);
        gui.addPanelListener(mainPanel);
    }

    public void initFrame() {
        frame = new Frame(500, 500, "Equation Calculator");

        frame.add(this);
        frame.setVisible(true);
        frame.connectGui(gui);
    }

    public void loop () {
        while (frame.isVisible()) {
            if (timer.checkDelay()) {
                gui.update(frame, frame.getBox());//update gui.

                frame.pullElement(mainPanel);//pull main panel on frame.

                repaint();
            }
        }
    }

    @Override
    public void paint (Graphics g) {
        super.paint(g);

        gui.draw(g, FrameUtils.getBox(frame));//draw gui panel.
    }
}