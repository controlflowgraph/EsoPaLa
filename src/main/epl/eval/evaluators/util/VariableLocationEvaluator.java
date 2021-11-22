package epl.eval.evaluators.util;

import epl.eval.Environment;
import epl.eval.evaluators.Evaluator;
import epl.eval.values.Value;
import epl.eval.values.VariableLocation;

public class VariableLocationEvaluator implements Evaluator
{
    private final Environment env;
    private final String variable;

    public VariableLocationEvaluator(Environment env, String variable)
    {
        this.env = env;
        this.variable = variable;
    }

    @Override
    public Value evaluate()
    {
        return new VariableLocation(this.env, this.variable);
    }
}
