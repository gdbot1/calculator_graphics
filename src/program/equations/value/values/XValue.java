package program.equations.value.values;

import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class XValue implements IValue {
    @Override
    public Float getValue(float x) {
        return x;
    }

    @Override
    public List<IValue> getArguments(String s) throws IOException {
        return null;
    }

    @Override
    public String toString() {
        return "x";
    }
}