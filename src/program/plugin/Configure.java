package program.plugin;

import program.equations.chain.functions.IFunction;

import java.util.HashMap;

public class Configure {
    public Configure() {

    }

    protected HashMap<String, IFunction> functions = new HashMap<>();

    public void addFunction(String syntax, IFunction function) {
        functions.put(syntax.toLowerCase(), function);
    }

    public HashMap<String, IFunction> getFunctions() {
        return this.functions;
    }
}
