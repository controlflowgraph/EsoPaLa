package epl.eval.evaluators.atom;

import epl.eval.evaluators.Evaluator;
import epl.eval.values.BooleanValue;
import epl.eval.values.Value;

public class BooleanEvaluator implements Evaluator
{
    private final String value;

    public BooleanEvaluator(String value)
    {
        this.value = value;
    }

    @Override
    public Value evaluate()
    {
        return new BooleanValue(this.value.equals("true"));
    }
}
