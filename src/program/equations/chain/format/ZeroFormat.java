package program.equations.chain.format;

import program.equations.chain.functions.IFunction;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public abstract class ZeroFormat extends AbstractFormat {
    public static final int count_of_arguments = 0;

    @Override
    public List<IValue> getArguments(String s) throws IOException {
        return null;
    }

    @Override
    public int getCountOfArguments() {
        return count_of_arguments;
    }
}