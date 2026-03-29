package program.equations.chain.chains.functions;

import program.equations.chain.format.SingleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class Absolute extends SingleFormat implements IFunction {
    public static final String syntax = "abs";

    public Absolute(IValue val) {
        super(val);
    }

    protected Absolute(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val == null) {
            return null;
        }

        return Math.abs(val.getValue(x));
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new Absolute(getArguments(s));
    }

    @Override
    public String toString() {
        return syntax + "(" + (val == null ? "null" : val.toString()) + ")";
    }
}