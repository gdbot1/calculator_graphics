package program.equations;

import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.awt.*;
import java.io.IOException;

public class Equation {
    protected String orig;
    protected IValue equation;
    protected Color color;

    public Equation(String orig, IValue equation) {
        this.orig = orig;
        this.equation = equation;

        generateColor();
    }

    public Equation(String orig) throws IOException {
        this.orig = orig;

        initEquation(orig);

        generateColor();
    }

    public Equation(IValue equation) {
        this.equation = equation;
        this.orig = equation.toString();

        generateColor();
    }

    public void initEquation(String orig) throws IOException {
        equation = StringBuilderUtils.getChain(orig);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void generateColor() {
        float max = (float)(255 * 3 / 2);

        int randRed = (int)(Math.random() * 255), randGreen = (int)(Math.random() * 255), randBlue = (int)(Math.random() * 255);

        int sum = randRed + randBlue + randGreen;

        int red = (int)Math.clamp(((float)randRed / sum) * max, 0, 255);
        int green = (int)Math.clamp(((float)randGreen / sum) * max, 0, 255);
        int blue = (int)Math.clamp(((float)randBlue / sum) * max, 0, 255);

        this.color = new Color(red, green, blue);
    }

    public String getOrig() {
        return orig;
    }

    public Color getColor() {
        return color;
    }

    public IValue getEquation() {
        return equation;
    }

}
