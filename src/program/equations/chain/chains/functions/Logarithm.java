package program.equations.chain.chains.functions;

import program.equations.chain.format.DoubleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class Logarithm extends DoubleFormat implements IFunction {
    public static final String syntax = "log";

    public Logarithm(IValue val1, IValue val2) {
        super(val1, val2);
    }

    protected Logarithm(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val1 == null || val2 == null) {
            return null;
        }

        return (float)(Math.log10(val2.getValue(x)) / Math.log10(val1.getValue(x)));
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new Logarithm(getArguments(s));
    }

    @Override
    public String toString() {
        return syntax + "(" + (val1  == null ? "null" : val1.toString()) + ", " + (val2  == null ? "null" : val2.toString()) + ")";
    }
}