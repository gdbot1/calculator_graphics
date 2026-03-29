package program.equations.chain.chains.bot1.conditions;

import program.equations.chain.format.ZeroFormat;
import program.equations.chain.functions.IFunction;
import program.equations.value.IValue;

public class NullFunc extends ZeroFormat implements IFunction {
    public static final String syntax = "null";

    @Override
    public Float getValue(float x) {
        return Float.NaN;
    }

    @Override
    public String toString() {
        return syntax + "()";
    }

    @Override
    public IValue getChain(String s) {
        return new NullFunc();
    }
}
