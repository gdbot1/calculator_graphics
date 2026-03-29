package program.exception;

import program.gui.alignment.Alignment;
import program.gui.element.elements.collection.panel.layout.layouts.boxLayout.Box;
import program.time.Timer;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExceptionManager implements ExceptionListener{
    protected CopyOnWriteArrayList<ExceptionBar> exceptions = new CopyOnWriteArrayList<>();

    public ExceptionManager() {

    }

    public void update() {
        exceptions.removeIf(ExceptionBar::isInactive);
    }

    public void draw(Graphics g, Box ranges) {
        for (ExceptionBar exception : exceptions) {
            exception.draw(g, ranges);
        }
    }

    @Override
    public void onException(IOException e) {
        exceptions.add(new ExceptionBar(e, Color.red, Alignment.TOP, Timer.getDelayInSeconds(5)));
    }
}
