package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.values.Value;
import epml.eval.values.VariableAccessor;

public class VariableEvaluator implements Evaluator
{
    private final Environment env;
    private final String variable;

    public VariableEvaluator(Environment env, String variable)
    {
        this.env = env;
        this.variable = variable;
    }

    @Override
    public Value evaluate()
    {
        return new VariableAccessor(this.env, this.variable);
    }
}
