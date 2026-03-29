package program.equations.chain.chains.functions.comparison;

import program.equations.chain.format.DoubleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class Maximal extends DoubleFormat implements IFunction {
    public static final String syntax = "max";

    public Maximal(IValue val1, IValue val2) {
        super(val1, val2);
    }

    protected Maximal(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val1 == null || val2 == null) {
            return null;
        }

        return Math.max(val1.getValue(x), val2.getValue(x));
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new Maximal(getArguments(s));
    }

    @Override
    public String toString() {
        return syntax + "(" + (val1  == null ? "null" : val1.toString()) + ", " + (val2  == null ? "null" : val2.toString()) + ")";
    }
}