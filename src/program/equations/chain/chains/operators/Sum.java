package program.equations.chain.chains.operators;

import program.equations.chain.format.DoubleFormat;
import program.equations.chain.operator.IOperator;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class Sum extends DoubleFormat implements IOperator {
    public static final String syntax = "sum";
    public static final String operator = "+";

    public Sum(IValue val1, IValue val2) {
        super(val1, val2);
    }

    protected Sum(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val1 == null || val2 == null) {
            return null;
        }

        return val1.getValue(x) + val2.getValue(x);
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new Sum(getArguments(s));
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
        return new Sum(left, right);
    }

    @Override
    public String toString() {
        return "(" + val1.toString() + " " +syntax + " " +val2.toString() + ")";
    }
}