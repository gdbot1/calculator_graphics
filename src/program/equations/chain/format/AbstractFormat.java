package program.equations.chain.format;

import program.equations.chain.functions.IFunction;
import program.equations.value.IValue;

import java.io.IOException;

public abstract class AbstractFormat implements IFunction, IFormat {
    @Override
    public IValue getChain(String s) throws IOException {
        return null;
    }
}
