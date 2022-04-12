package epl.eval.evaluators.util;

import epl.eval.evaluators.Evaluator;
import epl.eval.values.Value;

import java.util.List;

public class MultiEvaluator implements Evaluator
{
    private final List<Evaluator> evaluators;

    public MultiEvaluator(List<Evaluator> evaluators)
    {
        this.evaluators = evaluators;
    }


    @Override
    public Value evaluate()
    {
        Value value = null;
        for(Evaluator evaluator : this.evaluators)
        {
            value = evaluator.evaluate();
        }
        return value;
    }
}
