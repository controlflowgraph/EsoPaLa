package epml.eval.evaluators.comparator;

import epml.eval.Environment;
import epml.eval.Evaluator;
import epml.eval.SingleLanguagePattern;
import epml.eval.values.BooleanValue;
import epml.eval.values.NumberValue;
import epml.eval.values.Value;

@SingleLanguagePattern(pattern = "$a is greater than $b")
public class GreaterThanEvaluator implements Evaluator
{
    private final Evaluator a;
    private final Evaluator b;

    public GreaterThanEvaluator(Environment env, Evaluator a, Evaluator b)
    {
        this.a = a;
        this.b = b;
    }


    @Override
    public Value evaluate()
    {
        NumberValue a = this.a.evaluate().val(NumberValue.class);
        NumberValue b = this.b.evaluate().val(NumberValue.class);
        return new BooleanValue(a.value() > b.value());
    }
}
