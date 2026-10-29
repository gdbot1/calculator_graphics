package program.equations.chain.format;

import program.equations.value.IValue;

import java.io.IOException;
import java.util.List;

public interface IFormat {
    List<IValue> getArguments(String s) throws IOException;

    int getCountOfArguments();
}
