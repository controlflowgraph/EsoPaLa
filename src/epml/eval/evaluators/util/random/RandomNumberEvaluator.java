package epml.eval.evaluators.util.random;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "a random number")
public class RandomNumberEvaluator implements Evaluator
{
    public RandomNumberEvaluator(Environment env)
    {

    }

    @Override
    public Value evaluate()
    {
        return new NumberValue(Math.random());
    }
}
