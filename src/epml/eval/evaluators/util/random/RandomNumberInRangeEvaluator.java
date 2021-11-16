package epml.eval.evaluators.util.random;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "a random number between $lower and $upper")
public class RandomNumberInRangeEvaluator implements Evaluator
{
    private final Evaluator lower;
    private final Evaluator upper;

    public RandomNumberInRangeEvaluator(Environment env, Evaluator lower, Evaluator upper)
    {
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public Value evaluate()
    {
        NumberValue lower = this.lower.evaluate().val(NumberValue.class);
        NumberValue upper = this.upper.evaluate().val(NumberValue.class);
        return new NumberValue(Math.random() * (upper.value() - lower.value()) + lower.value());
    }
}
