package epl.eval.evaluators.atom;

import epl.eval.evaluators.Evaluator;
import epl.eval.values.NumberValue;
import epl.eval.values.Value;

public class NumberEvaluator implements Evaluator
{
    private final String number;

    public NumberEvaluator(String number)
    {
        this.number = number;
    }


    @Override
    public Value evaluate()
    {
        return new NumberValue(Double.parseDouble(this.number));
    }
}
