package program.plugin;

import program.equations.chain.functions.IFunction;
import program.equations.chain.operator.IOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Configure {
    public Configure() {

    }

    protected HashMap<String, IFunction> functions = new HashMap<>();
    protected List<List<IOperator>> operators = new ArrayList<>();

    public void addFunction(String syntax, IFunction function) {
        functions.put(syntax.toLowerCase(), function);
    }

    public HashMap<String, IFunction> getFunctions() {
        return this.functions;
    }

    public void addOperator(IOperator operator, int priority) {
        while (priority >= this.operators.size()) {
            this.operators.add(new ArrayList<>());
        }

        this.operators.get(priority).add(operator);
    }

    public List<List<IOperator>> getOperators() {
        return this.operators;
    }

    public List<IOperator> getOperatorsOfPriority(int priority) {
        if (priority >= this.operators.size()) {
            return new ArrayList<>();
        }

        return this.operators.get(priority);
    }
}
