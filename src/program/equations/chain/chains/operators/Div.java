package program.equations.chain.chains.operators;

import program.equations.chain.chains.functions.Pow;
import program.equations.chain.format.DoubleFormat;
import program.equations.chain.operator.IOperator;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class Div extends DoubleFormat implements IOperator {
    public static final String syntax = "div";
    public static final String operator = "/";

    public Div (IValue val1, IValue val2) {
        super(val1, val2);
    }

    protected Div (List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val1 == null || val2 == null) {
            return null;
        }

        return val1.getValue(x) / val2.getValue(x);
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new Div(getArguments(s));
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public IValue newInstance(IValue left, IValue right) {
        return new Div(left, right);
    }

    @Override
    public String toString() {
        return "(" + val1.toString() + " " + operator + " " +val2.toString() + ")";
    }
}