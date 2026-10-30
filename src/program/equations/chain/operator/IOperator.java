package program.equations.chain.operator;

import program.equations.value.IValue;

public interface IOperator extends IValue {
    String getOperator();

    IValue newInstance(IValue left, IValue right);
}