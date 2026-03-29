package program.equations.chain.chains;

import program.equations.chain.format.DoubleFormat;
import program.equations.value.IValue;

import java.util.List;

public class Sum extends DoubleFormat {
    public static final char syntax = '+';

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
    public String toString() {
        return "(" + val1.toString() + " " +syntax + " " +val2.toString() + ")";
    }
}