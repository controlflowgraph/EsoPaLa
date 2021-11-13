package epml.eval.evaluators.util.random;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.LanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@LanguagePattern(pattern = "a random number")
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
