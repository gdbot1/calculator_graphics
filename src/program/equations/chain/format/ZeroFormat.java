package program.equations.chain.format;

import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public abstract class ZeroFormat implements IValue {
    public static final int count_of_arguments = 0;

    public List<IValue> getArguments(String s) throws IOException {
        return null;
    }
}