package program.equations.chain.format;

import program.equations.utils.StringBuilderUtils;
import program.equations.value.IValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class TripleFormat implements IValue {
    public static final int count_of_arguments = 3;

    public IValue val1, val2, val3;

    protected TripleFormat(List<IValue> values) {
        this.val1 = values.get(0);
        this.val2 = values.get(1);
        this.val3 = values.get(2);
    }

    public TripleFormat(IValue val1, IValue val2, IValue val3) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }

    public List<IValue> getArguments(String s) throws IOException {
        List<String> separated_parts = StringBuilderUtils.separate(s, ',');

        if (separated_parts.size() != count_of_arguments) {
            throw new IOException("Count of arguments in " + getClass().getSimpleName() + " don't equals "+count_of_arguments+".");
        }

        List<IValue> args = new ArrayList<>();

        args.add(StringBuilderUtils.secondStage(separated_parts.get(0)));
        args.add(StringBuilderUtils.secondStage(separated_parts.get(1)));
        args.add(StringBuilderUtils.secondStage(separated_parts.get(2)));

        return args;
    }
}