package program.equations.chain.chains.bot1.logic;

import program.equations.chain.format.TripleFormat;
import program.equations.chain.functions.IFunction;
import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class SmartIfElseFunc extends TripleFormat implements IFunction {
    public static final String syntax = "if";

    public SmartIfElseFunc(IValue val1, IValue val2, IValue val3) {
        super(val1, val2, val3);
    }

    protected SmartIfElseFunc(List<IValue> values) {
        super(values);
    }

    @Override
    public Float getValue(float x) {
        if (val1 == null || val2 == null || val3 == null) {
            return null;
        }

        return val1.getValue(x) != 0 ? val2.getValue(x) : val3.getValue(x);
    }

    @Override
    public IValue getChain(String s) throws IOException {
        List<String> separated_parts = StringBuilderUtils.separate(s, ',');

        if (separated_parts.size() != count_of_arguments) {
            throw new IOException("Count of arguments in " + getClass().getSimpleName() + " don't equals "+count_of_arguments+".");
        }

        String condition = separated_parts.getFirst();

        List<String> condition_parts = StringBuilderUtils.separate(condition, '.'); // x/2<x/3 .< .> .|| .&& .==



        return new SmartIfElseFunc(val1, val2, val3);
    }

    @Override
    public String toString() {
        return syntax + "(" + (val1  == null ? "null" : val1.toString()) + ", " + (val2  == null ? "null" : val2.toString()) + ")";
    }
}
