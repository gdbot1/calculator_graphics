package program.equations.chain.chains.functions.trigonometry;

import program.equations.chain.format.SingleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class Sine extends SingleFormat implements IFunction {
    public static final String syntax = "sin";

    public Sine(IValue val) {
        super(val);
    }

    protected Sine(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val == null) {
            return null;
        }

        return (float)Math.sin(Math.toRadians(val.getValue(x)));
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new Sine(getArguments(s));
    }

    @Override
    public String toString() {
        return syntax + "(" + (val == null ? "null" : val.toString()) + ")";
    }
}