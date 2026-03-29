package program.equations.value.values;

import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public class Specific implements IValue {
    float value;

    public Specific(float value) {
        this.value = value;
    }

    @Override
    public Float getValue(float x) {
        return value;
    }

    @Override
    public List<IValue> getArguments(String s) throws IOException {
        return null;
    }

    @Override
    public String toString() {
        return value+"";
    }
}