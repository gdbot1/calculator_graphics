package program.equations.chain.chains.bot1.logical;

import program.equations.chain.format.DoubleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class LowerFunc extends DoubleFormat implements IFunction {
    public static final String syntax = "<";

    public LowerFunc(IValue val1, IValue val2) {
        super(val1, val2);
    }

    protected LowerFunc(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val1 == null || val2 == null) {
            return null;
        }

        return val1.getValue(x) < val2.getValue(x) ? 1f : 0f;
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new LowerFunc(getArguments(s));
    }

    @Override
    public String toString() {
        return syntax + "(" + (val1 == null ? "null" : val1.toString()) + ", " + (val2 == null ? "null" : val2.toString()) + ")";
    }
}