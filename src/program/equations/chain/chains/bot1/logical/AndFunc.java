package program.equations.chain.chains.bot1.logical;

import program.equations.chain.format.DoubleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class AndFunc extends DoubleFormat implements IFunction {
    public static final String syntax = "and";

    public AndFunc(IValue val1, IValue val2) {
        super(val1, val2);
    }

    protected AndFunc(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val1 == null || val2 == null) {
            return null;
        }

        return (float) (val1.getValue(x) != 0 && val2.getValue(x) != 0 ? 1 : 0);
    }

    @Override
    public IValue getChain(String s) throws IOException {
        return new AndFunc(getArguments(s));
    }

    @Override
    public String toString() {
        return syntax + "(" + (val1 == null ? "null" : val1.toString()) + ", " + (val2 == null ? "null" : val2.toString()) + ")";
    }
}