package program.equations.chain.chains.bot1.logical;

import program.equations.chain.format.DoubleFormat;
import program.equations.chain.format.TripleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class EqualsFunc extends TripleFormat implements IFunction {
    public static final String syntax = "==";

    public EqualsFunc(IValue val1, IValue val2, IValue val3) {
        super(val1, val2, val3);
    }

    protected EqualsFunc(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val1 == null || val2 == null || val3 == null) {
            return null;
        }

        return Math.abs(val1.getValue(x) - val2.getValue(x)) < val3.getValue(x) ? 1f : 0f;
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new EqualsFunc(getArguments(s));
    }

    @Override
    public String toString() {
        return syntax + "(" + (val1 == null ? "null" : val1.toString()) + ", " + (val2 == null ? "null" : val2.toString()) + ", " + (val3 == null ? "null" : val3.toString()) + ")";
    }
}