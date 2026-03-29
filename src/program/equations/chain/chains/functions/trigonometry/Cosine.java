package program.equations.chain.chains.functions.trigonometry;

import program.equations.chain.format.SingleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class Cosine extends SingleFormat implements IFunction {
    public static final String syntax = "cos";

    public Cosine(IValue val) {
        super(val);
    }

    protected Cosine(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val == null) {
            return null;
        }

        return (float)Math.cos(Math.toRadians(val.getValue(x)));
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new Cosine(getArguments(s));
    }

    @Override
    public String toString() {
        return syntax + "(" + (val == null ? "null" : val.toString()) + ")";
    }
}