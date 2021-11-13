package epml.eval.evaluators.util;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

public class NumberEvaluator implements Evaluator
{
    private final NumberValue number;

    public NumberEvaluator(String number)
    {
        this.number = new NumberValue(Double.parseDouble(number));
    }

    @Override
    public Value evaluate()
    {
        return this.number;
    }
}
