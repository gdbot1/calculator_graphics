package program.equations.chain.format;

import program.equations.chain.functions.IFunction;
import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class SingleFormat extends AbstractFormat {
    public static final int count_of_arguments = 1;

    public IValue val;

    protected SingleFormat(IValue val) {
        this.val = val;
    }

    public SingleFormat(List<IValue> values) {
        this.val = values.getFirst();
    }

    @Override
    public List<IValue> getArguments(String s) throws IOException {
        List<IValue> args = new ArrayList<>();

        args.add(StringBuilderUtils.resolveParenthesesStage(s));

        return args;
    }

    @Override
    public int getCountOfArguments() {
        return count_of_arguments;
    }
}