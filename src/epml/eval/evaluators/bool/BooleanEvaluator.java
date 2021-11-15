package epml.eval.evaluators.bool;

import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.BooleanValue;
import epml.eval.values.Value;

public class BooleanEvaluator implements Evaluator
{
    private final boolean value;

    public BooleanEvaluator(boolean value)
    {
        this.value = value;
    }

    @Override
    public Value evaluate()
    {
        return new BooleanValue(this.value);
    }
}
