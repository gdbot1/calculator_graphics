package program.equations.chain.chains.functions.constant;

import program.equations.chain.format.ZeroFormat;
import program.equations.chain.functions.IFunction;
import program.equations.value.IValue;

public class Pi extends ZeroFormat implements IFunction {
    public static final String syntax = "pi";

    @Override
    public Float getValue(float x) {
        return (float)Math.PI;
    }

    @Override
    public String toString() {
        return syntax + "()";
    }

    @Override
    public IValue getChain(String s) {
        return new Pi();
    }
}