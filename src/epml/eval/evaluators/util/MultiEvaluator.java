package epml.eval.evaluators.util;

import epml.eval.Evaluator;
import epml.eval.values.NoneValue;
import epml.eval.values.Value;

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
        Value value = new NoneValue();
        for(Evaluator evaluator : this.evaluators)
        {
            value = evaluator.evaluate();
        }
        return value;
    }

    public List<Evaluator> getEvaluators()
    {
        return evaluators;
    }
}
