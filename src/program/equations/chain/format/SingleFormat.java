package program.equations.chain.format;

import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class SingleFormat implements IValue {
    public static final int count_of_arguments = 1;

    public IValue val;

    protected SingleFormat(IValue val) {
        this.val = val;
    }

    public SingleFormat(List<IValue> values) {
        this.val = values.getFirst();
    }

    public List<IValue> getArguments(String s) throws IOException {
        List<IValue> args = new ArrayList<>();

        args.add(StringBuilderUtils.secondStage(s));

        return args;
    }
}