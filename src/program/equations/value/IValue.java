package program.equations.value;

import java.io.IOException;
import java.util.List;

public interface IValue {
    Float getValue(float x);

    List<IValue> getArguments(String s) throws IOException;

    String toString();
}